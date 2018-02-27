package org.spacedrones.software;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemMessageService extends AbstractSoftware implements MessageMediator {

	private List<SpacecraftBusComponent> registeredComponents;

	@Override
	public String getDescription() {
		return "System message mediator";
	}

	public SystemMessageService(String name) {
		super(name);
		registeredComponents = new ArrayList<SpacecraftBusComponent>();
	}

	@Override
	public SystemStatusMessage addComponent(SpacecraftBusComponent component) {
		SystemStatusMessage message = new SystemStatusMessage(this, component.name() +
				" registered with the messaging system", Status.INFO);
		registeredComponents.add(component);
		return message;
	}


	@Override
	public Message sendMessageTo(Message message, SpacecraftBusComponent component) {
		int componentIndex = registeredComponents.indexOf(component);
		boolean componentIsRegistered = componentIndex != -1;
		if(componentIsRegistered)
			return registeredComponents.get(componentIndex).recieveBusMessage(message);
		else
			return new SystemMessage(null, null, "Taxonomic not registered");
	}


	@Override
	public Map<String, Message> broadcastMessage(Message message) {
		Map<String, Message> replies = new HashMap<String, Message>();
		for(SpacecraftBusComponent component : registeredComponents) {
			replies.put(component.id(), component.recieveBusMessage(message));
		}
		return replies;
	}


	@Override
	public String description() {
		return "A system service to handle sytem messaging.";
	}


}

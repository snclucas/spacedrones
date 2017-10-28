package org.spacedrones.software;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.status.SystemStatusMessage;

public class SystemMessageService extends AbstractSoftware implements MessageMediator {

	public static TypeInfo typeID = new TypeInfo("MessageMediatorSoftware");
	
	private List<SpacecraftBusComponent> registeredComponents;
	
	@Override
	public TypeInfo getType() {
		return typeID;
	}
	
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
		SystemStatusMessage message = new SystemStatusMessage(this, component.getName() + 
				" registered with the messaging system", getSystemComputer().getUniversalTime(), Status.INFO);
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
			return new SystemMessage(null, null, "Component not registered", getSystemComputer().getUniversalTime());
	}


	@Override
	public Map<String, Message> broadcastMessage(Message message) {
		Map<String, Message> replies = new HashMap<String, Message>();
		for(SpacecraftBusComponent component : registeredComponents) {
			replies.put(component.getId(), component.recieveBusMessage(message));
		}
		return replies;
	}

	
	@Override
	public String describe() {
		return "A system service to handle sytem messaging.";
	}


}

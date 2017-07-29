package org.spacedrones.software;

import java.util.Map;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.status.SystemStatusMessage;

public interface MessageMediator extends Software {
	
	Message sendMessageTo(Message message, SpacecraftBusComponent component);
	
	Map<String, Message> broadcastMessage(Message message);
	 
	SystemStatusMessage addComponent(SpacecraftBusComponent component);

}

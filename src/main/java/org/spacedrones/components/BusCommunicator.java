package org.spacedrones.components;

import org.spacedrones.software.Message;

public interface BusCommunicator {
	
	Message recieveBusMessage(Message message);

}

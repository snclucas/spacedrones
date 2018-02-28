package org.spacedrones.status;

import org.spacedrones.components.comms.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SystemStatus {

	private StatusProvider statusProvider;
	private List<SystemStatusMessage> messages;


	public SystemStatus(StatusProvider statusProvider) {
		super();
		this.statusProvider = statusProvider;
		this.messages = new ArrayList<SystemStatusMessage>();
	}

	public SystemStatus mergeSystemStatus(SystemStatus systemStatus) {
		//Merge all messages
		this.messages.addAll(systemStatus.getMessages());
		return this;
	}

	public void addSystemMessage(SystemStatusMessage systemStatusMessage) {
		this.messages.add(systemStatusMessage);
	}

	public void mergeSystemMessages(List<SystemStatusMessage> systemStatusMessages) {
		this.messages.addAll(systemStatusMessages);
	}

	public void addSystemMessage(String message, String status) {
		this.messages.add(new SystemStatusMessage(statusProvider, message, status));
	}



	public boolean hasCriticalMessages() {
		return checkForSystemStatusMessages(Status.CRITICAL).size() > 0;
	}

	public boolean hasWarningMessages() {
		return checkForSystemStatusMessages(Status.WARNING).size() > 0;
	}

	public boolean isOK() {
		return checkForSystemStatusMessages(Status.OK, Status.WARNING, Status.INFO).size() == messages.size();
	}

	public List<SystemStatusMessage> getMessages() {
		return messages;
	}


	@Override
	public String toString() {
		return "SystemStatus//"+ statusProvider.name() + "// [messages=" + messages
				+ "]";
	}


	public int getNumberOfMessages() {
		return messages.size();
	}

	// -- Private methods -- //

	private List<SystemStatusMessage> checkForSystemStatusMessages(String ... systemStatusMessageTypes) {
		List<SystemStatusMessage> filteredMessages = new ArrayList<SystemStatusMessage>();
		for(SystemStatusMessage message : messages)
			for(String status : systemStatusMessageTypes)
				if(Objects.equals(message.getStatus(), status))
					filteredMessages.add(message);
		return filteredMessages;
	}



}

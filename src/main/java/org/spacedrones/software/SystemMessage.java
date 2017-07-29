package org.spacedrones.software;

import org.spacedrones.components.Identifiable;

public class SystemMessage implements Message {
	
	private Identifiable reciever;
	private Identifiable sender;
	private String messageBody;
	private double universalDate;
	
	
	public SystemMessage(Identifiable reciever, Identifiable sender,
			String messageBody, double universalDate) {
		super();
		this.reciever = reciever;
		this.sender = sender;
		this.messageBody = messageBody;
		this.universalDate = universalDate;
	}

	@Override
	public String getRecieverIdent() {
		return reciever.getIdent();
	}

	@Override
	public String getSenderIdent() {
		return sender.getIdent();
	}

	@Override
	public String getMessage() {
		return messageBody;
	}

	@Override
	public double getUniversalDate() {
		return universalDate;
	}

}

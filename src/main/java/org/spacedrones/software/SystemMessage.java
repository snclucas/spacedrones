package org.spacedrones.software;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.universe.*;

public class SystemMessage implements Message {

	private Taxonomic reciever;
	private Taxonomic sender;
	private String messageBody;
	private double universalDate;


	public SystemMessage(Taxonomic reciever, Taxonomic sender,
                       String messageBody) {
		super();
		this.reciever = reciever;
		this.sender = sender;
		this.messageBody = messageBody;
		this.universalDate = Universe.getUniversalTime();
	}

	@Override
	public String getRecieverId() {
		return reciever.id();
	}

	@Override
	public String getSenderId() {
		return sender.id();
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

package org.spacedrones.status;

import java.text.DecimalFormat;

import org.spacedrones.components.comms.Status;

public class SystemStatusMessage {
	
	private StatusProvider statusProvider;
	private String message;
	private double universalDate;
	private Status status;
	
	
	public SystemStatusMessage(StatusProvider statusProvider, String message, double universalDate, Status status) {
		super();
		this.statusProvider = statusProvider;
		this.message = message;
		this.universalDate = universalDate;
		this.status = status;
	}
	
	
	public StatusProvider getStatusProvider() {
		return this.statusProvider;
	}


	public String getMessage() {
		return message;
	}


	public double getUniversalDate() {
		return universalDate;
	}


	public Status getStatus() {
		return status;
	}


	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("0000.0000000000000");
		return format.format(universalDate) + " : " + statusProvider.getName() +": [message=" + message + " status=" + status + "]";
	}

}

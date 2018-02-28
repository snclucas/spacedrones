package org.spacedrones.status;

import org.spacedrones.universe.Universe;

import java.text.DecimalFormat;

public class SystemStatusMessage {

	private StatusProvider statusProvider;
	private String message;
	private double universalDate;
	private String status;


	public SystemStatusMessage(StatusProvider statusProvider, String message, String status) {
		super();
		this.statusProvider = statusProvider;
		this.message = message;
		this.universalDate = Universe.getUniversalTime();
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


	public String getStatus() {
		return status;
	}


	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("0000.0000000000000");
		return format.format(universalDate) + " : " + statusProvider.name() +": [message=" + message + " status=" + status + "]";
	}

}

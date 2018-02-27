package org.spacedrones.status;

import java.text.DecimalFormat;

import org.spacedrones.components.comms.Status;
import org.spacedrones.universe.*;

public class SystemStatusMessage {

	private StatusProvider statusProvider;
	private String message;
	private double universalDate;
	private Status status;


	public SystemStatusMessage(StatusProvider statusProvider, String message, Status status) {
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


	public Status getStatus() {
		return status;
	}


	@Override
	public String toString() {
		DecimalFormat format = new DecimalFormat("0000.0000000000000");
		return format.format(universalDate) + " : " + statusProvider.name() +": [message=" + message + " status=" + status + "]";
	}

}

package org.spacedrones.components.comms;

public class Status {

	public Status(String typeId) {

	}

	public static Status CRITICAL = new Status("CRITICAL");
	public static Status NOT_PERMITTED = new Status("NOT_PERMITTED");
	public static Status NOT_ENOUGH_POWER = new Status("NOT_ENOUGH_POWER");
	public static Status NOT_ENOUGH_CPU = new Status("NOT_ENOUGH_CPU");
	public static Status PROBLEM = new Status("PROBLEM");
	public static Status WARNING = new Status("WARNING");
	public static Status INFO = new Status("INFO");
	public static Status OK = new Status("OK");
	public static Status PERMITTED = new Status("PERMITTED");
	public static Status SUCCESS = new Status("SUCCESS");
}

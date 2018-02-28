package org.spacedrones.components.comms;

public class Status {

	public Status(String typeId) {

	}

	public static String CRITICAL = "CRITICAL";
	public static String NOT_PERMITTED = "NOT_PERMITTED";
	public static String NOT_ENOUGH_POWER = "NOT_ENOUGH_POWER";
	public static String NOT_ENOUGH_CPU = "NOT_ENOUGH_CPU";
	public static String PROBLEM = "PROBLEM";
	public static String WARNING = "WARNING";
	public static String INFO = "INFO";
	public static String OK = "OK";
	public static String PERMITTED = "PERMITTED";
	public static String SUCCESS = "SUCCESS";

  @Override
  public String toString() {
    return "Status{}";
  }
}

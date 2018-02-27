package org.spacedrones.software;

public interface Message {
	String getRecieverId();
	
	String getSenderId();
	
	String getMessage();
	
	double getUniversalDate();	
	
}

package org.spacedrones.software;

public interface Message {

	String getRecieverIdent();
	
	String getSenderIdent();
	
	String getMessage();
	
	double getUniversalDate();	
	
}

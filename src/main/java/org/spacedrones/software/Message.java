package org.spacedrones.software;

import org.spacedrones.components.TypeInfo;

public interface Message {
	TypeInfo category = new TypeInfo("Message");
	TypeInfo type = category;

	String getRecieverId();
	
	String getSenderId();
	
	String getMessage();
	
	double getUniversalDate();	
	
}

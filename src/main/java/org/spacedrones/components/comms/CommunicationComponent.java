package org.spacedrones.components.comms;

import java.util.List;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface CommunicationComponent extends SpacecraftBusComponent, BusCommunicator {
	
	public static TypeInfo categoryID = new TypeInfo("CommunicationDevice");
	
	int TYPE = "Communication device".hashCode();
	
	void broadcast(String message, int recipient);
	
	boolean hasRecievedMessages();
	
	List<String> getMessages();
	
	boolean addMessageToQueue(String message);
	
	void setPowerLevel(double powerLevel);

	double getRange();
	
	boolean requestRange(double range);
	
	double getMaximumRange();
	
	void setDeviceNoiseLevel(double deviceNoiseLevel);
	double getDeviceNoiseLevel();
	
	double getEfficiency();
	void setEfficiency(double efficiency);
	
}

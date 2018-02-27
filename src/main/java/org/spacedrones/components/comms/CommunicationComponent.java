package org.spacedrones.components.comms;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;

import java.util.List;

public interface CommunicationComponent extends SpacecraftBusComponent, BusCommunicator {

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

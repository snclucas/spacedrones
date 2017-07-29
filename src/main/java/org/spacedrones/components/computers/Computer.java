package org.spacedrones.components.computers;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.software.Software;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.status.SystemStatusMessage;

public interface Computer extends SpacecraftBusComponent, BusCommunicator  {
	
	static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	static TypeInfo type() {
		return category();
	}
	
	SystemComputer getSystemComputer();

	// Software handling
	Software getSoftware(TypeInfo softwareType);
	boolean hasSoftware(TypeInfo softwareType);
	SystemStatusMessage loadSoftware(Software software);
	
	Bus getSpacecraftBus();
	double getMaxCPUThroughput();
	
	
	
	void registerSpacecraftBus(Bus bus);
}

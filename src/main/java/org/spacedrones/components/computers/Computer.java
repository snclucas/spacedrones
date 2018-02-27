package org.spacedrones.components.computers;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.software.Software;
import org.spacedrones.status.SystemStatusMessage;

public interface Computer extends SpacecraftBusComponent, BusCommunicator  {
	SystemComputer getSystemComputer();

	DataStore getStorageDevice();

	Software getSoftware(String softwareType);

	boolean hasSoftware();
	boolean hasSoftwareType(String softwareType);

	SystemStatusMessage loadSoftware(Software software);

	double getMaxCPUThroughput();
}

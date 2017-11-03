package org.spacedrones.components.computers;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.software.Software;
import org.spacedrones.status.SystemStatusMessage;

public interface Computer extends SpacecraftBusComponent, BusCommunicator  {
	TypeInfo category = new TypeInfo("Computer");
	TypeInfo type = category;

	SystemComputer getSystemComputer();

	DataStore getStorageDevice();

	Software getSoftware(TypeInfo softwareType);

	boolean hasSoftware();
	boolean hasSoftwareType(TypeInfo softwareType);

	SystemStatusMessage loadSoftware(Software software);

	double getMaxCPUThroughput();
}

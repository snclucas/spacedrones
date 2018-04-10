package org.spacedrones.components.computers;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Software;
import org.spacedrones.status.SystemStatusMessage;

import java.util.Map;

public interface Computer extends SpacecraftBusComponent, BusCommunicator  {
	SystemComputer getSystemComputer();

	DataStore getStorageDevice();

  Map<String, Software> getSoftware();
	Software getSoftware(String softwareType);

	boolean hasSoftware();
	boolean hasSoftwareType(String softwareType);

	SystemStatusMessage loadSoftware(Software software);

	double getCPUThroughputAvailable(Unit unit);
}

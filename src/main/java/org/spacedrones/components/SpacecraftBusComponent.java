package org.spacedrones.components;

import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.status.SystemStatusMessage;

public interface SpacecraftBusComponent extends PhysicalComponent, Tickable, Diagnosable, StatusProvider, Onlineable, BusCommunicator   {
	
	void registerWithBus(Bus bus);

	double getNominalPower(Unit unit);
	
	double getNominalCPUThroughput(Unit unit);
	
	double getMaximumOperationalPower(Unit unit);

	double getMaximumOperationalCPUThroughput(Unit unit);

	double getCurrentPower(Unit unit);
	double getCurrentCPUThroughput(Unit unit);
	
	SystemComputer getSystemComputer();
	
	SystemStatusMessage registerSystemComputer(SystemComputer systemComputer);

	boolean isOnSpacecraftBus();
	
	double getUniversalTime();
}

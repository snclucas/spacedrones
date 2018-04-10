package org.spacedrones.components;

import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

public interface SpacecraftBusComponent extends PhysicalComponent, Tickable, StatusProvider, Onlineable, BusCommunicator  {

	double getNominalPower(Unit unit);
	double getNominalCPUThroughput(Unit unit);

	double getMaximumPower(Unit unit);
	double getMaximumCPUThroughput(Unit unit);

	double getCurrentPower(Unit unit);
	double getCurrentCPUThroughput(Unit unit);

	SystemComputer getSystemComputer();

	SystemStatusMessage registerSystemComputer(SystemComputer systemComputer);

	SystemStatus runDiagnostics(int level);
}

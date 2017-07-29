package org.spacedrones.components;

import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.status.SystemStatusMessage;

public interface SpacecraftBusComponent extends Component, Diagnosable, StatusProvider,  Onlineable, BusCommunicator   {
	
	void registerWithBus(Bus bus);
	
	double getNominalPower();
	double getNominalPower(Unit unit);
	
	void setNominalPower(double nominalPower);
	
	double getNominalCPUThroughput();
	void setNominalCPUThroughput(double nominalCPUThroughput);
	
	double getMaximumOperationalPower();
	
	double getMaximumOperationalCPUThroughput();
	double getMaximumOperationalCPUThroughput(Unit unit);
	
	double getCurrentPower();
	double getCurrentPower(Unit unit);
	double getCurrentCPUThroughput();
	double getCurrentCPUThroughput(Unit unit);
	
	SystemComputer getSystemComputer();
	
	SystemStatusMessage registerSystemComputer(SystemComputer systemComputer);

	boolean isOnSpacecraftBus();
	void accept(ComponentVisitor componentVisitor);
	
	double getUniversalTime();
}

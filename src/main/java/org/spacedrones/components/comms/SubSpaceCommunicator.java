package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class SubSpaceCommunicator extends AbstractCommunicationComponent {

	SubSpaceCommunicator(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification, propagationModel);
	}

	public String description() {
		return "Sub-space communication device";
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(name() + " online.", Status.OK);
		return systemStatus;
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit);
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		// Nominal and operation CPU are the same for this XX change
		return getNominalCPUThroughput(unit);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);

		if("propagationModel" == null)
			systemStatus.addSystemMessage(
					"Level " + level + "diagnostics : Problem. No propagation model.", Status.PROBLEM);
		else
			systemStatus.addSystemMessage(
					"Running diagnostics [level " + level + "].", Status.OK);

		return systemStatus;
	}

}

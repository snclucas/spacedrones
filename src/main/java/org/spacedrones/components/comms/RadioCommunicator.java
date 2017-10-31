package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class RadioCommunicator extends AbstractCommunicationComponent {
	
	public static TypeInfo type() {
		return new TypeInfo("RadioCommunicator");
	}

	public RadioCommunicator(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification, propagationModel);
	}

	public String describe() {
		return "RF communication device";
	}

	public TypeInfo getType() {
		return type();
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit);
	}
	
	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		// Nominal and operation CPU are the same
		return getNominalCPUThroughput(unit);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);

		if ("propagationModel" == null)
			systemStatus.addSystemMessage(
							"Level " + level + "diagnostics : Problem. No propagation model.", getUniversalTime(), Status.PROBLEM);
		else
			systemStatus.addSystemMessage(
							"Running diagnostics [level " + level + "].", getUniversalTime(), Status.OK);

		return systemStatus;
	}
	
}

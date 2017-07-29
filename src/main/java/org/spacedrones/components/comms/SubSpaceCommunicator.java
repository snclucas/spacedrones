package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class SubSpaceCommunicator extends AbstractCommunicationComponent {
	
	public static TypeInfo type() {
		return new TypeInfo("SubSpaceCommunicator");
	}

	public SubSpaceCommunicator(String name, BusComponentSpecification busResourceSpecification, Model propagationModel) {
		super(name, busResourceSpecification, propagationModel);
	}

	@Override
	public String describe() {
		return "Sub-space communication device";
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	public TypeInfo getType() {
		return type();
	}

	
	@Override
	public double getCurrentPower() {
		return getNominalPower();
	}
	
	@Override
	public double getCurrentCPUThroughput() {
		// Nominal and operation CPU are the same for this XX change
		return getNominalCPUThroughput();
	}
	
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		
		if(propagationModel == null)
			systemStatus.addSystemMessage(
					"Level " + level + "diagnostics : Problem. No propagation model.", getUniversalTime(), Status.PROBLEM);
		else 
			systemStatus.addSystemMessage(
					"Running diagnostics [level " + level + "].", getUniversalTime(), Status.OK);
		
		return systemStatus;
	}
	
	
	
	
}

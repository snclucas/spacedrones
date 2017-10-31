package org.spacedrones.profiles;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.status.SystemStatus;

public class SimpleLinearThrustProfile extends AbstractProfile implements ThrustProfile {
	
	public static TypeInfo type = new TypeInfo("SimpleLinearThrustProfile");

	public SimpleLinearThrustProfile(String name) {
		super(name);
	}

	@Override
	public double getNormalizedThrust(double powerLevel) {
		return powerLevel;
	}
	
	@Override
	public double getNormalizedPower(double powerLevel) {
		return powerLevel;
	}

	@Override
	public double getNormalizedCPU(double powerLevel) {
		return powerLevel;
	}
	
	@Override
	public String toString() {
		return "Simple Linear Thrust Model";
	}

	@Override
	public double[][] getNormalizedThrustProfile() {
		double[] profile = new double[10];
		for(int i = 0; i<10;i++)
			profile[i] =  1.0 / (10-i);
		return new double[][]{profile};
	}
	
	@Override
	public double[][] getNormalizedPowerProfile() {
		double[] profile = new double[10];
		for(int i = 0; i<10;i++)
			profile[i] =  1.0 / (10-i);
		return new double[][]{profile};
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something better here
		SystemStatus systemStatus = new SystemStatus(this);
		if(validateModel())
			systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		else
			systemStatus.addSystemMessage("Diagnostic [" + getName() +"] PROBLEM", -1, Status.PROBLEM);
		return systemStatus;
	}
	
	private boolean validateModel() {
		return true;
	}

	@Override
	public String describe() {
		return "Simple linear thrust profile.";
	}
	
}

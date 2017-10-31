package org.spacedrones.profiles;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.status.SystemStatus;

public class SimpleLinearFuelConsumptionProfile extends AbstractProfile implements FuelConsumptionProfile {
	
	public static TypeInfo type = new TypeInfo("SimpleLinearFuelConsumptionProfile");

	public SimpleLinearFuelConsumptionProfile(String name) {
		super(name);
	}

	@Override
	public double getNormalizedFuelConsumption(double powerLevel) {
		return powerLevel;
	}

	@Override
	public String toString() {
		return "Simple Linear Fuel Consumption";
	}

	@Override
	public double[][] getNormalizedFuelConsumptionProfile() {
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
		return "Simple linear fuel consumption profile.";
	}
	
}

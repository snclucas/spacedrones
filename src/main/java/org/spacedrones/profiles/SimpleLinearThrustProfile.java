package org.spacedrones.profiles;

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
	public String description() {
		return "Simple linear thrust profile.";
	}
	
}

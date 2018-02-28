package org.spacedrones.profiles;

public class SimpleLinearFuelConsumptionProfile extends AbstractProfile implements FuelConsumptionProfile {

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
	public String description() {
		return "Simple linear fuel consumption profile.";
	}

}

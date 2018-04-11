package org.spacedrones.profiles;

public class SimpleLinearFuelConsumptionProfile extends AbstractProfile implements FuelConsumptionProfile {

  private final double minimum;
  private final double maximum;

	public SimpleLinearFuelConsumptionProfile(String name, double minimum, double maximum) {
		super(name);
		this.minimum = minimum;
		this.maximum = maximum;
	}

  public double getMinimum() {
    return minimum;
  }

  public double getMaximum() {
    return maximum;
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

}

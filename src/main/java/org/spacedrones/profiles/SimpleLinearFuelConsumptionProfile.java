package org.spacedrones.profiles;

import org.spacedrones.physics.Unit;

public class SimpleLinearFuelConsumptionProfile extends AbstractProfile implements FuelConsumptionProfile {

  private final double minFlowRateInM3s;
  private final double maxFlowRateInM3s;

	public SimpleLinearFuelConsumptionProfile(String name, double minFlowRate, double maxFlowRate, Unit unit) {
		super(name);
		this.minFlowRateInM3s = minFlowRate * unit.value(); // Set value to m3
		this.maxFlowRateInM3s = maxFlowRate * unit.value();
	}

  public double getMinFlowRate(Unit unit) {
    return minFlowRateInM3s / unit.value();
  }

  public double getMaxFlowRate(Unit unit) {
    return maxFlowRateInM3s / unit.value();
  }

  @Override
	public double getNormalizedFuelConsumption(double powerLevel) {
		return powerLevel;
	}

	@Override
	public String toString() {
		return "Simple Linear LiquidFuel Consumption";
	}

	@Override
	public double[][] getNormalizedFuelConsumptionProfile(int steps) {
		double[] profile = new double[steps];
		for(int i = 0; i<steps;i++)
			profile[i] =  1.0 / (steps-i);
		return new double[][]{profile};
	}

}

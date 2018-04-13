package org.spacedrones.profiles;

import org.spacedrones.physics.Unit;
import org.spacedrones.status.StatusProvider;

public interface FuelConsumptionProfile extends StatusProvider {
	double getNormalizedFuelConsumption(double powerLevel);
	double[][] getNormalizedFuelConsumptionProfile(int steps);
  double getMinFlowRate(Unit unit);
  double getMaxFlowRate(Unit unit);
}

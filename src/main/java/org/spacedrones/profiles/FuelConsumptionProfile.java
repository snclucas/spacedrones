package org.spacedrones.profiles;

import org.spacedrones.status.StatusProvider;

public interface FuelConsumptionProfile extends StatusProvider {
	double getNormalizedFuelConsumption(double powerLevel);
	double[][] getNormalizedFuelConsumptionProfile();
}

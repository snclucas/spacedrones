package org.spacedrones.components.propulsion;

import org.spacedrones.components.propulsion.thrust.FuelSubSystem;
import org.spacedrones.profiles.FuelConsumptionProfile;

public interface FuelConsumer {
	double getFuelConsumptionRate();
	double getFuelConsumptionRate(double powerLevel);
	void setFuelSubSystem(FuelSubSystem fuelSubSystem);
	
	FuelConsumptionProfile getFuelConsumptionProfile();
	void setFuelConsumptionProfile(FuelConsumptionProfile fuelConsumptionProfile);
}

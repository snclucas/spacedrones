package org.spacedrones.components.propulsion;

import org.spacedrones.components.propulsion.thrust.SimpleFuelSubSystem;
import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.FuelConsumptionProfile;

public interface FuelConsumer {
	double getFuelConsumptionRate(Unit unit);
	double getFuelConsumptionRate(double powerLevel, Unit unit);
	void setFuelSubSystem(SimpleFuelSubSystem fuelSubSystem);

	FuelConsumptionProfile getFuelConsumptionProfile();
	void setFuelConsumptionProfile(FuelConsumptionProfile fuelConsumptionProfile);
}

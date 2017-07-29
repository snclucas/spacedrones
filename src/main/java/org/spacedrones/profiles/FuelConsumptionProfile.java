package org.spacedrones.profiles;

import org.spacedrones.components.Diagnosable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.status.StatusProvider;

public interface FuelConsumptionProfile extends Diagnosable, StatusProvider {
	
	TypeInfo categoryID = new TypeInfo("FuelConsumptionProfile");

	double getNormalizedFuelConsumption(double powerLevel);
	double[][] getNormalizedFuelConsumptionProfile();
}

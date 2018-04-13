package org.spacedrones.profiles;

import org.spacedrones.physics.Unit;

public class FuelConsumptionProfileFactory {

	public static String SIMPLE_LINEAR = "Simple Linear";

	public static FuelConsumptionProfile getFuelConsumptionProfile(String fuelConsumptionProfileType){
		if(fuelConsumptionProfileType.equalsIgnoreCase(SIMPLE_LINEAR)){
			return new SimpleLinearFuelConsumptionProfile(SIMPLE_LINEAR, 0, 100, Unit.ls);
		}
		return null;
	}

}

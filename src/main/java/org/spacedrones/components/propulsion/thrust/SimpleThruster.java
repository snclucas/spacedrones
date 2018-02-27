package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.profiles.FuelConsumptionProfile;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class SimpleThruster extends AbstractThrustingFuelConsumingEngine {

	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, 
			ThrustProfile thrustModel, FuelConsumptionProfile fuelConsumptionModel, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel, fuelConsumptionModel,
				engineVector, vectored);
	}
	
	
	public SimpleThruster(String name, BusComponentSpecification busResourceSpecification, double maximumThrust, EngineVector engineVector, 
			boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}

	@Override
	public void setFuelConsumptionProfile(final FuelConsumptionProfile fuelConsumptionProfile) {

	}

}

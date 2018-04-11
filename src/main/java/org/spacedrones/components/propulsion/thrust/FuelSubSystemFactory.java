package org.spacedrones.components.propulsion.thrust;

import java.security.InvalidParameterException;

import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.*;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;

public class FuelSubSystemFactory {


	public static SimpleFuelSubSystem getFuelSubsystem(String fuelSubsystem, int fuelSubsystemType) throws InvalidParameterException{

		switch (fuelSubsystem) {

		case SimpleFuelSubSystem.BASIC_FUEL_SUBSYSTEM:

			double mass = 250 * Unit.kg.value();
			double volume = 10.0 * Unit.m3.value();
			double nominalPower = 100 * Unit.W.value();
			double nominalCPUThroughput = 10 * Unit.kFLOPs.value();

			BusComponentSpecification busSpecs = new BusComponentSpecification(
					new PhysicalSpecification(mass, volume),
					new OperationalSpecification(nominalPower, nominalCPUThroughput));

      FuelConsumptionProfile fuelConsumptionProfile =
              FuelConsumptionProfileFactory.getFuelConsumptionProfile(FuelConsumptionProfileFactory.SIMPLE_LINEAR);

			SimpleFuelSubSystem fuelDeliverySubSystem = new SimpleFuelSubSystem(SimpleFuelSubSystem.BASIC_FUEL_SUBSYSTEM,
					busSpecs, fuelSubsystemType, fuelConsumptionProfile);

			return fuelDeliverySubSystem;
		default:
			throw new InvalidParameterException("No such fuel subsystem.");

		}


	}



}

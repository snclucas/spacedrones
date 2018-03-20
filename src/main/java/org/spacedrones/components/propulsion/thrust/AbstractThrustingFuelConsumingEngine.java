package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.profiles.FuelConsumptionProfile;
import org.spacedrones.profiles.SimpleLinearFuelConsumptionProfile;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

import java.util.List;

public abstract class AbstractThrustingFuelConsumingEngine extends AbstractThrustingEngine implements FuelConsumingEngine {

	private final FuelConsumptionProfile fuelConsumptionProfile;
	private FuelSubSystem fuelSubSystem;

	AbstractThrustingFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustProfile thrustModel, FuelConsumptionProfile fuelConsumptionModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, thrustModel,
				engineVector, vectored);
		this.fuelConsumptionProfile = fuelConsumptionModel;
	}


	AbstractThrustingFuelConsumingEngine(String name, BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust,
				engineVector, vectored);
		this.fuelConsumptionProfile = new SimpleLinearFuelConsumptionProfile("Linear model");
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = super.online();

		List<FuelSubSystem> busComponents = getSystemComputer().findComponentByType(FuelSubSystem.class);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {
				if( ((FuelSubSystem)component).getFuelSubsystemType() == FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", Status.OK);
					FuelSubSystem fuelSubSystem = (FuelSubSystem)busComponents.get(0);
					if(fuelSubSystem.hasFuelTanks() == false)
						systemStatus.addSystemMessage("No fuel storage tanks found",
										Status.WARNING);
					else
						systemStatus.addSystemMessage(fuelSubSystem.getFuelTanks().size() +  " fuel tank(s) found", Status.OK);
				}
			}
		}
		else {
			systemStatus.addSystemMessage("No fuel subsystem found",
							Status.WARNING);
		}
		systemStatus.addSystemMessage(name() + " online.", Status.OK);
		return systemStatus;
	}

	@Override
	public double getFuelConsumptionRate() {
		return fuelConsumptionProfile.getNormalizedFuelConsumption(this.powerLevel);
	}

	@Override
	public double getFuelConsumptionRate(double powerLevel) {
		return fuelConsumptionProfile.getNormalizedFuelConsumption(powerLevel);
	}

	@Override
	public void setFuelSubSystem(FuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}

	@Override
	public FuelConsumptionProfile getFuelConsumptionProfile() {
		return this.fuelConsumptionProfile;
	}

	@Override
	public void tick(double dt) {
	}

}

package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.physics.*;
import org.spacedrones.profiles.FuelConsumptionProfile;
import org.spacedrones.profiles.SimpleLinearFuelConsumptionProfile;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

import java.util.List;

public abstract class AbstractThrustingFuelConsumingEngine extends AbstractThrustingEngine implements FuelConsumingEngine {

	private final FuelConsumptionProfile fuelConsumptionProfile;
	private SimpleFuelSubSystem fuelSubSystem;

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
		this.fuelConsumptionProfile = new SimpleLinearFuelConsumptionProfile("Linear model", 0, 100);
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = super.online();

		List<SimpleFuelSubSystem> busComponents = getSystemComputer().findComponentByType(SimpleFuelSubSystem.class);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {
				if( ((SimpleFuelSubSystem)component).getFuelSubsystemType() == SimpleFuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", Status.OK);
					SimpleFuelSubSystem fuelSubSystem = (SimpleFuelSubSystem)busComponents.get(0);
					if(fuelSubSystem.hasFuelTank() == false)
						systemStatus.addSystemMessage("No fuel storage tanks found",
										Status.WARNING);
					else
						systemStatus.addSystemMessage("Fuel tank(s) found", Status.OK);
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
  public double getMass(Unit unit) {
    return super.getMass(unit) + fuelSubSystem.getMass(unit);
  }

  @Override
	public double getFuelConsumptionRate() {
		return getFuelConsumptionRate(this.powerLevel);
	}

	@Override
	public double getFuelConsumptionRate(double powerLevel) {
		return fuelConsumptionProfile.getNormalizedFuelConsumption(powerLevel) *
            (fuelConsumptionProfile.getMaximum() - fuelConsumptionProfile.getMinimum());
	}

	@Override
	public void setFuelSubSystem(SimpleFuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}

	@Override
	public FuelConsumptionProfile getFuelConsumptionProfile() {
		return this.fuelConsumptionProfile;
	}

	@Override
	public void tick(double dt) {
	  fuelSubSystem.setFuelFlowRate(getFuelConsumptionRate());
	  fuelSubSystem.tick(dt);
	}

}

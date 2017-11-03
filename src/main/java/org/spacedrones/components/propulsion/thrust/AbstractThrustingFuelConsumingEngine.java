package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
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

	public TypeInfo type() {
		return type;
	}
	
	@Override
	public TypeInfo category() {
		return category;
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = super.online();
		
		List<SpacecraftBusComponent> busComponents = getSystemComputer().findComponentByType(FuelSubSystem.typeID);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {	
				if( ((FuelSubSystem)component).getFuelSubsystemType() == FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", getSystemComputer().getUniversalTime(), Status.OK);
					FuelSubSystem fuelSubSystem = (FuelSubSystem)busComponents.get(0);
					if(fuelSubSystem.hasFuelTanks() == false)
						systemStatus.addSystemMessage("No fuel storage tanks found", 
								getSystemComputer().getUniversalTime(), Status.WARNING);
					else
						systemStatus.addSystemMessage(fuelSubSystem.getFuelTanks().size() +  " fuel tank(s) found", getUniversalTime(), Status.OK);
				}
			}
		}
		else {
			systemStatus.addSystemMessage("No fuel subsystem found", 
					getSystemComputer().getUniversalTime(), Status.WARNING);
		}
		systemStatus.addSystemMessage(getName() + " online.", getSystemComputer().getUniversalTime(), Status.OK);
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

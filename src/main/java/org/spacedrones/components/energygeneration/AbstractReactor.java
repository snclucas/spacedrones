package org.spacedrones.components.energygeneration;

import java.util.List;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.FuelConsumer;
import org.spacedrones.components.propulsion.thrust.FuelSubSystem;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractReactor extends AbstractPowerGenerator implements FuelConsumer  {
	
	protected FuelSubSystem fuelSubSystem;
	
	public AbstractReactor(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);

	}

	
	@Override
	public void setFuelSubSystem(FuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}

	
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		
		List<SpacecraftBusComponent> busComponents = getSystemComputer().findComponentByCategory(FuelSubSystem.categoryID);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {	
				if( ((FuelSubSystem)component).getFuelSubsystemType() == FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", getUniversalTime(), Status.OK);
					FuelSubSystem fuelSubSystem = (FuelSubSystem)busComponents.get(0);
					if(!fuelSubSystem.hasFuelTanks())
						systemStatus.addSystemMessage("No fuel storage tanks found", 
								getUniversalTime(), Status.WARNING);
					else
						systemStatus.addSystemMessage(fuelSubSystem.getFuelTanks().size() +  " fuel tank(s) found", getUniversalTime(), Status.OK);
				}
			}
		}
		else {
			systemStatus.addSystemMessage("No fuel subsystem found", 
					getUniversalTime(), Status.WARNING);
		}
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus;
	}
	
	

}

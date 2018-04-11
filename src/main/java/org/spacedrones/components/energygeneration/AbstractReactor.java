package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.FuelConsumer;
import org.spacedrones.components.propulsion.thrust.SimpleFuelSubSystem;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

import java.util.List;

public abstract class AbstractReactor extends AbstractPowerGenerator implements FuelConsumer  {

	protected SimpleFuelSubSystem fuelSubSystem;

	public AbstractReactor(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);

	}


	@Override
	public void setFuelSubSystem(SimpleFuelSubSystem fuelSubSystem) {
		this.fuelSubSystem = fuelSubSystem;
	}



	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);

		List<SimpleFuelSubSystem> busComponents = getSystemComputer().findComponentByType(SimpleFuelSubSystem.class);

		if(busComponents.size() > 0) {
			for(SpacecraftBusComponent component : busComponents) {
				if( ((SimpleFuelSubSystem)component).getFuelSubsystemType() == SimpleFuelSubSystem.PROPULSION_FUEL_SUBSYSTEM) {
					systemStatus.addSystemMessage("Propulsion fuel subsystem found", Status.OK);
					SimpleFuelSubSystem fuelSubSystem = (SimpleFuelSubSystem)busComponents.get(0);
					if(!fuelSubSystem.hasFuelTank())
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



}

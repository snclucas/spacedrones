package org.spacedrones.spacecraft;

import java.util.List;

import org.spacedrones.Configuration;
import org.spacedrones.components.Component;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.SystemData;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;


public abstract class AbstractSpacecraft implements Spacecraft {
	
	private String name;
	private boolean online;
	
	private double spacecraftBusComponentsVolumeRequirement;
	private double spacecraftBusComponentsMass;
	
	protected boolean systemsOnline;
	protected Hull hull;
	
	protected Bus bus;
	
	protected final String ident;
	
	
	
	public AbstractSpacecraft(String name, Hull hull) {
		this.name = name;
		setHull(hull);
		this.bus = new SpacecraftBus("Spacecraft bus", this);
		this.bus.setSpacecraft(this);
		systemsOnline = false;
		this.ident = Configuration.getUUID();
	}

	@Override
	public String getName() {
		return this.name;
	}


	@Override
	public TypeInfo getCategory() {
		return Spacecraft.category;
	}
	
	@Override
	public Bus getSpacecraftBus() {
		return bus;
	}

	@Override
	public void setSpacecraftBus(Bus bus) {
		this.bus = bus;
	}
	
	@Override
	public boolean isOnline() {
		return this.online;
	}


	@Override
	public SystemStatus online() {
		SystemStatus status = new SystemStatus(this);
		
		SpacecraftFirmware.scanSpacecraftComponents(bus);
		
		if(SpacecraftFirmware.bootstrapSystemComputer(bus) == false) {
			status.addSystemMessage("No system computer found! Aborting spacecraft onlining.", 11, Status.CRITICAL);
			systemsOnline = false;
			online = false;
			return status;
		}
		else {
			//Online the system computer
			SystemStatus systemComputerStatus = bus.getSystemComputer().online();
			status.mergeSystemStatus(systemComputerStatus);
			if(status.isOK()) {
				systemsOnline = true;
				online = true;
				SystemData data = new SystemData("spaceraft-ident", getSpacecraftBus().getSpacecraft().getIdent());
				
				bus.getSystemComputer().getStorageDevice().saveData(data);
				
			}
		}
		return status;
	}
	
	
	protected boolean isSystemsOnline() {
		return systemsOnline;
	}
	
	@Override
	public Hull getHull() {
		return hull;
	}


	private void setHull(Hull hull) {
		this.hull = hull;
	}


	@Override
	public void addComponent(Component component) {
		if(component instanceof SpacecraftBusComponent == false)
			throw new ComponentConfigurationException("Cano only add SpacecraftBusComponents");
		bus.addComponent((SpacecraftBusComponent)component);
		((SpacecraftBusComponent)component).registerWithBus(bus);
	}
	
	
	public List<SpacecraftBusComponent> getComponents() {
		return bus.getComponents();
	}


	//Hull delegate methods
	@Override
	public double getLength() {
		return hull.getLength();
	}

	@Override
	public double getWidth() {
		return hull.getWidth();
	}

	@Override
	public double getVolume() {
		return hull.getVolume();
	}
	
	@Override
	public double getMass() {
		return hull.getMass() + bus.getComponents().stream().mapToDouble(f-> f.getMass()).sum();
	}


	public double getImpactResistance() {
		return hull.getImpactResistance();
	}


	public double getEMResistance() {
		return hull.getEMResistance();
	}


	public double getRadiationResistance() {
		return hull.getRadiationResistance();
	}


	public double getThermalResistance() {
		return hull.getThermalResistance();
	}


	
	public double getTotalMassOfComponents() {
		return spacecraftBusComponentsMass;
	}


	public double getTotalVolumeOfComponents() {
		//Adjust total volume calculation for the hull as the hull actually provides volume not uses it.
		spacecraftBusComponentsVolumeRequirement -= this.getHull().getVolume();
		return spacecraftBusComponentsVolumeRequirement;
	}


	public double getTotalPowerRequirementOfSpacecraftBusComponents() {
		return SpacecraftFirmware.getTotalPowerAvailable(bus);
	}


	public double getTotalCPURequirementOfSpacecraftBusComponents() {
		return SpacecraftFirmware.getTotalCPUThroughputAvailable(bus);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((hull == null) ? 0 : hull.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSpacecraft other = (AbstractSpacecraft) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (hull == null) {
			if (other.hull != null)
				return false;
		} else if (!hull.equals(other.hull))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	@Override
	public String getIdent() {
		return ident;
	}

	
	@Override
	public void tick() {
		getComponents().forEach(Component::tick);
	}

}

package org.spacedrones.spacecraft;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.*;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractSpacecraft implements Spacecraft {

	private final String name;
  private final String id;
  private final Hull hull;

	private boolean online = false;

	private double spacecraftBusComponentsVolumeRequirement;
	private double spacecraftBusComponentsMass;

	private boolean systemsOnline;

  private List<SpacecraftBusComponent> components = new ArrayList<>();

	AbstractSpacecraft(String name, String id, Hull hull) {
		this.name = name;
		this.hull = hull;
		this.id = id;
	}

  //private List<SpacecraftBusComponent> getComponents() {
  //  return spacecraftFirmware.getComponents();
  //}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public boolean isOnline() {
		return this.online;
	}

	@Override
	public SystemStatus online() {
		SystemStatus status = new SystemStatus(this);

		SpacecraftFirmware.scanSpacecraftComponents(components);

		if(!SpacecraftFirmware.bootstrapSystemComputer(components)) {
			status.addSystemMessage("No system computer found! Aborting spacecraft onlining.", Status.CRITICAL);
			systemsOnline = false;
			online = false;
			return status;
		}
		else {
			//Online the system computer
      SystemComputer systemComputer = SpacecraftFirmware.getSystemComputer(components);
      systemComputer.setComponents(components);
			SystemStatus systemComputerStatus = systemComputer.online();
			status.mergeSystemStatus(systemComputerStatus);
			if(status.isOK()) {
				systemsOnline = true;
				online = true;
				SystemData systemData = new SystemData("spaceraft-ident", "");
				DataRecord<SystemData> data = new DataRecord<>(systemData.getId(), SystemData.class.getSimpleName(), systemData);
        SpacecraftFirmware.getSystemComputer(components).getStorageDevice().saveData(data);
			}
		}
		return status;
	}

	protected boolean isSystemsOnline() {
		return systemsOnline;
	}

	public Hull getHull() {
		return hull;
	}

	//Hull delegate methods

	public PhysicalSpecification getPhysicalSpecification() {
	  return new PhysicalSpecification(getMass(Unit.kg),
            hull.getVolume(Unit.m3),
            hull.getLength(Unit.m),
            hull.getWidth(Unit.m),
            1);
  }


	public double getMass(Unit unit) {
		return hull.getMass(unit) + components.stream().mapToDouble(f-> f.getMass(unit)).sum();
	}

	public double getLength(Unit unit) {
	  return hull.getLength(unit);
  }

  public double getWidth(Unit unit) {
    return hull.getWidth(unit);
  }

  public double getHeight(Unit unit) {
    return hull.getHeight(unit);
  }

  public double getVolume(Unit unit) {
    return hull.getVolume(unit);
  }

	public double getTotalVolumeOfComponents(Unit unit) {
		//Adjust total volume calculation for the hull as the hull actually provides volume not uses it.
		spacecraftBusComponentsVolumeRequirement -= this.getHull().getVolume(unit);
		return spacecraftBusComponentsVolumeRequirement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
	public String id() {
		return id;
	}

	@Override
	public void tick(double dt) {
    components.forEach(c-> c.tick(dt));
	}

  public void giveManagerHandleTo(SpacecraftManager other) {
    other.receiveManagerHandle(new Handle());
  }

  public class Handle {
    public List<SpacecraftBusComponent> getSCComponents() { return components; }
		public Hull getHull() { return hull; }
    private Handle() { }
  }

}

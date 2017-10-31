package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.software.Software;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatusMessage;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractComputer extends AbstractBusComponent implements Computer {

	private boolean online;
  private Bus spacecraftBus;
	private final Map<TypeInfo, Software> loadedSoftware;
	
	AbstractComputer(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
    loadedSoftware = new HashMap<>();
	}
	
	public SystemComputer getSystemComputer() {
		return getSpacecraftBus().getSystemComputer();
	}

	@Override
	public SystemStatusMessage loadSoftware(Software software) {
		software.setComputer(this);
		if(loadedSoftware.put(software.getType(), software) != null)
			return new SystemStatusMessage(this, software.getDescription() + " software loaded", getUniversalTime(), Status.OK);
		else 
			return new SystemStatusMessage(this, software.getDescription() + " software replaced exisiting software", getUniversalTime(), Status.OK);
	}

	@Override
	public Software getSoftware(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType);
	}

	@Override
	public boolean hasSoftwareType(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType) != null;
	}

  @Override
  public boolean hasSoftware() {
    return loadedSoftware.size() > 0;
  }

  @Override
  public void registerSpacecraftBus(Bus spacecraftBus) {
    this.spacecraftBus = spacecraftBus;
  }
	
	@Override
	public Bus getSpacecraftBus() {
	  if(spacecraftBus != null) {
      return spacecraftBus;
    }
    else {
	    throw new ComponentConfigurationException("Computer " + this.getName() + " is not registreed with the bus");
    }
	}

	@Override
	public double getMaxCPUThroughput() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("Computer");
	}
	
	@Override
	public void tick() {
	}

  @Override
  public boolean isOnline() {
    return online;
  }

  public void setOnline(final boolean online) {
    this.online = online;
  }

  @Override
	public TypeInfo getType() {
		return new TypeInfo("Computer");
	}

	@Override
	public TypeInfo getCategory() {
		return new TypeInfo("Computer");
	}
	

}

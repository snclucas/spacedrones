package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.software.Software;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractComputer extends AbstractBusComponent implements Computer {

	private boolean online;
	private final Map<TypeInfo, Software> loadedSoftware;

  private final double maxCPUThroughput;

  private final DataStore storageDevice;

	AbstractComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification);
    loadedSoftware = new HashMap<>();
    this.maxCPUThroughput = maxCPUThroughput;
    storageDevice = DataStoreFactory.getDataStore(DataStoreFactory.BASIC_DATASTORE);
	}

  @Override
  public DataStore getStorageDevice() {
    return storageDevice;
  }

  public double getMaxCPUThroughput() {
    return maxCPUThroughput;
  }

	@Override
	public SystemStatusMessage loadSoftware(Software software) {
		software.setComputer(this);
		if(loadedSoftware.put(software.type(), software) != null)
			return new SystemStatusMessage(this, software.getDescription() + " software loaded", Status.OK);
		else
			return new SystemStatusMessage(this, software.getDescription() + " software replaced exisiting software", Status.OK);
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
  public void tick(double dt) {
  }

  @Override
  public boolean isOnline() {
    return online;
  }

  public void setOnline(final boolean online) {
    this.online = online;
  }

  @Override
  public SystemStatus online() {
    //SystemStatus systemStatus = super.online();
    SystemStatus systemStatus = new SystemStatus(this);

    if(hasSoftware()) {
      systemStatus.addSystemMessage("No interface software loaded", Status.WARNING);
    }
    return systemStatus;
  }


  // ----- Taxonomy

  @Override
  public TypeInfo type() {
    return new TypeInfo(this.getClass().getSimpleName());
  }

  @Override
  public TypeInfo category() {
    return type();
  }

}

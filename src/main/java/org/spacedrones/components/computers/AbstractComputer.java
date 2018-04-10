package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Software;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractComputer extends AbstractBusComponent implements Computer {

	private boolean online;
	private final Map<String, Software> loadedSoftware;

  private final double maxCPUThroughputAvailable;

  private final DataStore storageDevice;

	AbstractComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughputAvailable) {
		super(name, busResourceSpecification);
    this.loadedSoftware = new HashMap<>();
    this.maxCPUThroughputAvailable = maxCPUThroughputAvailable;
    this.storageDevice = DataStoreFactory.getDataStore(DataStoreFactory.BASIC_DATASTORE);
	}

  @Override
  public DataStore getStorageDevice() {
    return storageDevice;
  }

  @Override
  public double getCPUThroughputAvailable(Unit unit) {
    return maxCPUThroughputAvailable / unit.value();
  }

	@Override
	public SystemStatusMessage loadSoftware(Software software) {
		software.setComputer(this);
		if(loadedSoftware.put(software.getClass().getSimpleName(), software) != null)
			return new SystemStatusMessage(this, software.getDescription() + " software loaded", Status.OK);
		else
			return new SystemStatusMessage(this, software.getDescription() + " software replaced exisiting software", Status.OK);
	}

	@Override
  public Software getSoftware(String softwareType) {
    return loadedSoftware.get(softwareType);
  }

  @Override
  public Map<String, Software> getSoftware() {
    return loadedSoftware;
  }

	@Override
	public boolean hasSoftwareType(String softwareType) {
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

}

package org.spacedrones.components;

import org.spacedrones.Configuration;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.universe.UniverseAware;

import java.util.Calendar;


public abstract class AbstractBusComponent extends UniverseAware implements SpacecraftBusComponent {

  private boolean online = false;
	private final String name;
	private final String id;
	private SystemComputer systemComputer;

	private final BusComponentSpecification busResourceSpecification;
	
	private double currentPower;
	private double currentCPUThroughput;
	

	public AbstractBusComponent(String name, BusComponentSpecification busSpec) {
		this.name = name;
		this.busResourceSpecification = busSpec;
		this.currentPower = busSpec.getNominalPower(Unit.MW);
		this.currentCPUThroughput = busSpec.getNominalCPUThroughout(Unit.MFLOPs);
		this.id = Configuration.getUUID();
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + ":\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}

  public final SystemStatusMessage registerSystemComputer(SystemComputer systemComputer) {
    this.systemComputer = systemComputer;
    return new SystemStatusMessage(this, this.name + " registered with " + systemComputer.getName(), getUniversalTime(), Status.INFO);
	}

  public BusComponentSpecification getBusResourceSpecification() {
    return busResourceSpecification;
  }

  @Override
	public final String getId() {
		return this.id;
	}

	@Override
	public boolean isOnline() {
		return this.online;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getMass(Unit unit) {
		return busResourceSpecification.getMass(unit);
	}

	@Override
	public double getVolume(Unit unit) {
		return busResourceSpecification.getVolume(unit);
	}

	public void setVolume(double volume) {
		busResourceSpecification.setVolume(volume);
	}

	public double getNominalPower(Unit unit) {
		return busResourceSpecification.getNominalPower(unit) / unit.value();
	}

	@Override
	public double getNominalCPUThroughput(Unit unit) {
		return busResourceSpecification.getNominalCPUThroughout(unit);
	}

	@Override
	public double getMaximumOperationalPower(Unit unit) {
		return busResourceSpecification.getMaximumOperationalPower(unit);
	}

	@Override
	public double getMaximumOperationalCPUThroughput(Unit unit) {
		return busResourceSpecification.getMaximumOperationalCPUThroughput(unit) / unit.value();
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return currentPower * (online ? 1 : 0) / unit.value();
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return currentCPUThroughput * (online ? 1 : 0) / unit.value();
	}

	public SystemComputer getSystemComputer() {
		if(!isRegisteredWithSystemComputer())
			throw new ComponentConfigurationException(this.name +
              " is not registered with the computer");
		return systemComputer;
	}
	
	private boolean isRegisteredWithSystemComputer() {
		return this.systemComputer != null;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
    online = this.isRegisteredWithSystemComputer();
    if(online) {
      systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
    }
		else {
      systemStatus.addSystemMessage(getName() + " not registered with system computer.", getUniversalTime(), Status.CRITICAL);
    }
			return systemStatus;
	}

	@Override
	public double getUniversalTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);

		//Change this;
		return ((year) + day/365.0 +
						(hour/(365*24.0)) +
						(minute/(365.0*24.0*60.0)) +
						(second/(365.0*86400.0)) +
						(millisecond/(365.0*86400000.0)));
	}

}

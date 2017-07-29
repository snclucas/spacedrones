package org.spacedrones.components;

import org.spacedrones.Configuration;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.universe.UniverseAware;


public abstract class AbstractBusComponent extends UniverseAware implements SpacecraftBusComponent {

	protected boolean online;
	
	protected String name;
	
	protected final String ident;

	protected Bus spacecraftBus;
	
	protected SystemComputer systemComputer;
	
	protected BusComponentSpecification busResourceSpecification;
	
	private double currentPower;
	private double currentCPUThroughput;
	

	public AbstractBusComponent(String name, BusComponentSpecification busResourceSpecification) {
		super();
		this.name = name;
		this.busResourceSpecification = busResourceSpecification;
		this.currentPower = busResourceSpecification.getNominalPower();
		this.currentCPUThroughput = busResourceSpecification.getNominalCPUThroughout();
		this.online = false;
		this.ident = Configuration.getUUID();
	}
	
	
	@Override
	public boolean isOnSpacecraftBus() {
		return spacecraftBus != null;
	}
	

	@Override
	public void registerWithBus(Bus bus) {
		this.spacecraftBus = bus;
	}
	
	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + ":\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	

	@Override
	public SystemStatusMessage registerSystemComputer(SystemComputer systemComputer) {
		this.systemComputer = systemComputer;
		return new SystemStatusMessage(this, this.name + " registered with " + systemComputer.getName(), getUniversalTime(), Status.INFO);
	}



	@Override
	public String getIdent() {
		return this.ident;
	}


	@Override
	public boolean isOnline() {
		return this.online;
	}
	

	@Override
	public void accept(ComponentVisitor componentVisitor) {
		componentVisitor.visit(this);
	}

	@Override
	public String getName() {
		return this.name;
	}
	

	@Override
	public double getMass() {
		return busResourceSpecification.getMass();
	}
	
	
	@Override
	public double getMass(Unit unit) {
		return busResourceSpecification.getMass(unit);
	}
	

	@Override
	public void setMass(double mass) {
		busResourceSpecification.setMass(mass);
	}
	

	@Override
	public double getVolume() {
		return busResourceSpecification.getVolume();
	}
	
	@Override
	public double getVolume(Unit unit) {
		return busResourceSpecification.getVolume(unit);
	}

	
	public void setVolume(double volume) {
		busResourceSpecification.setVolume(volume);
	}

	
	public double getNominalPower() {
		return busResourceSpecification.getNominalPower();
	}
	
	
	public double getNominalPower(Unit unit) {
		return busResourceSpecification.getNominalPower() / unit.value();
	}

	
	@Override
	public void setNominalPower(double nominalPower) {
		busResourceSpecification.setNominalPower(nominalPower);
	}

	
	@Override
	public double getNominalCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout();
	}

	
	@Override
	public void setNominalCPUThroughput(double nominalCPUThroughput) {
		busResourceSpecification.setNominalCPUThroughout(nominalCPUThroughput);
	}
	
	
	@Override
	public double getMaximumOperationalPower() {
		return busResourceSpecification.getMaximumOperationalPower();
	}
	
	
	@Override
	public double getMaximumOperationalCPUThroughput() {
		return busResourceSpecification.getMaximumOperationalCPUThroughput();
	}
	
	
	@Override
	public double getMaximumOperationalCPUThroughput(Unit unit) {
		return busResourceSpecification.getMaximumOperationalCPUThroughput() / unit.value();
	}

	
	public void setMaximumOperationalCPUThroughput(double maximumOperationalCPUThroughput) {
		busResourceSpecification.setMaximumOperationalCPUThroughput(maximumOperationalCPUThroughput);
	}
	
	
	

	@Override
	public double getCurrentPower() {
		return currentPower * (isOnline()?1:0);
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getCurrentPower() / unit.value();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return currentCPUThroughput * (isOnline()?1:0);
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return currentCPUThroughput * (isOnline()?1:0) / unit.value();
	}


	public SystemComputer getSystemComputer() {
		if(isRegisteredWithSystemComputer() == false)
			throw new ComponentConfigurationException(this.name + " is not registered with the computer");
		return spacecraftBus.getSystemComputer();
	}

	//@Override
	//public SystemStatusMessage registerWithSystemComputer(SystemComputer systemComputer) {
	//	getSystemComputer().registerSpacecraftComponents()
	//	this.systemComputer = systemComputer;
	//	return new SystemStatusMessage(this,getName() + " registering with systemcomputer", systemComputer.getUniversalTime(), Status.INFO);
	//}
	
	
	public boolean isRegisteredWithSystemComputer() {
		return this.systemComputer != null;
	}


	@Override
	public String toString() {
		return getName();
	}

	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		if(this.isRegisteredWithSystemComputer())
			systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		else
			systemStatus.addSystemMessage(getName() + " not registered with system computer.", getUniversalTime(), Status.CRITICAL); 
		return systemStatus; 
	}


	@Override
	public double getUniversalTime() {
		return getSystemComputer().getUniversalTime();
	}
	
	

}

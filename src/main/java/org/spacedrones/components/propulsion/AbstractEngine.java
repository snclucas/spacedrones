package org.spacedrones.components.propulsion;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.PropulsionManagementSoftware;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractEngine extends AbstractBusComponent implements Engine {

	
	protected double powerLevel;
	protected EngineVector engineVector;
	protected boolean vectored;
	protected double requestedPowerLevel;
	private EngineVector requestedEngineVector;

	public AbstractEngine(String name, BusComponentSpecification busResourceSpecification, 
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification);

		this.engineVector = engineVector;
		this.requestedEngineVector = engineVector;
		this.vectored = vectored;
		this.powerLevel = 0;
		this.requestedPowerLevel = 0;
	}


	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		if(!getSystemComputer().hasSoftware(PropulsionManagementSoftware.type))
			systemStatus.addSystemMessage("No engine management software loaded", getUniversalTime(), Status.PROBLEM);
		else
			systemStatus.addSystemMessage("Engine management software loaded", getUniversalTime(), Status.OK);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus;	
	}


	@Override
	public void execute() {
		this.powerLevel = this.requestedPowerLevel;
		this.engineVector = this.requestedEngineVector;
	}
	
	
	@Override
	public void setPowerLevel(double powerLevel) {
		if(powerLevel < 0 || powerLevel > 100)
			throw new IllegalArgumentException("Power level must be btween 0 and 100");
		this.powerLevel = powerLevel;
	}
	

	@Override
	public double getPowerLevel() {
		return powerLevel;
	}


	public BusRequirement callVector(EngineVector engineVector) {
		this.requestedEngineVector = engineVector;
		double requiredPower = getCurrentPower(Unit.MW);
		double requiredCPUThroughput = getCurrentCPUThroughput(Unit.MFLOP);
		return new BusRequirement(requiredPower, requiredCPUThroughput);
	}


	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}


	@Override
	public EngineVector getEngineVector() {
		return engineVector;
	}
	

	@Override
	public boolean isVectored() {
		return vectored;
	}


	@Override
	public double getCurrentPower(Unit unit) {
		return getRequiredPower(this.powerLevel, unit);
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getRequiredCPUThroughput(this.powerLevel, unit);
	}


	@Override
	public String describe() {
		String description = " -- Simple Ion Engine -- \n" +
						"Mass: " + getMass(Unit.kg) + " Kg, Volume: " + getVolume(Unit.m3) + " m3, Power: <thrust dep.> GJ/s, CPU: " + getNominalCPUThroughput(Unit.MFLOP) + " GFLOPS";
		description += "\n";
		description += "   ---------------------------------------------";
		return description;
	}


}

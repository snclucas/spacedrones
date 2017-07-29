package org.spacedrones.components.energygeneration;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

public class SimpleSolarArray extends AbstractPowerGenerator {
	
	private EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();
	
	private double arrayArea;
	private final double efficiency;
	
	public SimpleSolarArray(String name, BusComponentSpecification busResourceSpecification, 
			double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
		this.arrayArea = arrayArea;
		this.efficiency = efficiency;
		this.maxPower = arrayArea*efficiency*10.0*Unit.kW.value();
	}
	

	@Override
	public double getCurrentPower() {
		// Nominal and operation power are the same for this hull
		return getNominalPower();
	}
	
	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	

	@Override
	public double getCurrentCPUThroughput() {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput();
	}


	public double getArrayArea() {
		return arrayArea;
	}


	public double getEfficiency() {
		return efficiency;
	}
	
	

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	

	@Override
	public String describe() {
		return toString();
	}

	@Override
	public String toString() {
		return "SimpleSolarArray [arrayArea=" + arrayArea + ", efficiency="
				+ efficiency + ", lightFlux=" + getLightFlux() + "]";
	}


	@Override
	public double getPowerOutput() {
		return arrayArea*efficiency*getLightFlux();
	}

	
	private double getLightFlux() {
		Coordinates coordinates = Universe.getInstance()
				.getSpacecraftLocation(getSystemComputer().getSpacecraftBus().getSpacecraft().getIdent());
		return environmentDataProvider.getEnvironmentData(coordinates).getSolarFlux();
	}
	
	
	public static TypeInfo type() {
		return new TypeInfo("SimpleSolarArray");
	}
	
	@Override
	public TypeInfo getType() {
		return type();
	}
	

}

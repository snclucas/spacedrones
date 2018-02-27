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
	public static TypeInfo type = new TypeInfo("SimpleSolarArray");

	private EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();

	private double arrayArea;
	private final double efficiency;

	public SimpleSolarArray(String name, BusComponentSpecification busResourceSpecification,
			double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
		this.arrayArea = arrayArea;
		this.efficiency = efficiency;
		setMaxPower(arrayArea*efficiency*10.0*Unit.kW.value());
	}


	@Override
	public double getCurrentPower(Unit unit) {
		// Nominal and operation power are the same for this hull
		return getNominalPower(unit);
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", Status.OK);
		return systemStatus;
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput(unit);
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
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", Status.OK);
		return systemStatus;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage);
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
		String spacecraftIdent = (String)(this.getSystemComputer().getSystemData("spaceraft-ident"));
		Coordinates coordinates = Universe.getInstance()
				.getSpacecraftLocation(spacecraftIdent);
		return environmentDataProvider.getEnvironmentData(coordinates).getSolarFlux();
	}

	@Override
	public TypeInfo type() {
		return type;
	}

}

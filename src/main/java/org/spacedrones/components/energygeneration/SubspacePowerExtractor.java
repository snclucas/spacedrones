package org.spacedrones.components.energygeneration;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class SubspacePowerExtractor extends AbstractPowerGenerator {
	public static TypeInfo type = new TypeInfo("SubspacePowerExtractor");

	private final double arrayArea;
	private final double efficiency;

	public SubspacePowerExtractor(String name, BusComponentSpecification busResourceSpecification,
			double arrayArea, double efficiency) {
		super(name, busResourceSpecification);
		this.arrayArea = arrayArea;
		this.efficiency = efficiency;
		setMaxPower(arrayArea*efficiency*10.0*Unit.kW.value());
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit);
	}


	public double getArrayArea() {
		return arrayArea;
	}

	public double getEfficiency() {
		return efficiency;
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
		return "SubEtherPowerGenerator [maximumPowerOutputFromEther="
				+ getMaximumPowerOutput() + "]";
	}

	@Override
	public double getPowerOutput() {
		return 0;
	}

}

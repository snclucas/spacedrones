package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class BasicSensorArray extends AbstractSensor {
	public static TypeInfo type = new TypeInfo("BasicSensorArray");

	final private int numberOfSensorElements;

	BasicSensorArray(String name, BusComponentSpecification busResourceSpecification,
									 SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile);
		this.numberOfSensorElements = numberOfSensorElements;
	}

	@Override
	public double getSensorGain() {
		return numberOfSensorElements * getSensorProfile().getSignalGain();
	}

	@Override
	public double getMass(Unit unit) {
		return super.getMass(unit) * numberOfSensorElements;
	}

	@Override
	public double getVolume(Unit unit) {
		return super.getVolume(unit) * numberOfSensorElements;
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit) * numberOfSensorElements;
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getNominalCPUThroughput(unit) * numberOfSensorElements;
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getSystemComputer().getUniversalTime(), Status.OK);
		return systemStatus; 
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		SystemStatus systemStatus = new SystemStatus(this);
		// TODO Change this
		systemStatus.addSystemMessage(
				"Level " + level + "diagnostics : All OK.", getSystemComputer().getUniversalTime(), Status.OK);

		return systemStatus;
	}

}

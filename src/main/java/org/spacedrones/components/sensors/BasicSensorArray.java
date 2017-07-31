package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class BasicSensorArray extends AbstractSensor {

	private int numberOfSensorElements;

	BasicSensorArray(String name, BusComponentSpecification busResourceSpecification,
									 SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile);
		this.numberOfSensorElements = numberOfSensorElements;
	}

	@Override
	public TypeInfo getType() {
		return new TypeInfo("BasicSensorArray");
	}

	@Override
	public double getSensorGain() {
		return numberOfSensorElements * sensorProfile.getSignalGain();
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
		return busResourceSpecification.getNominalPower(unit) * numberOfSensorElements;
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return busResourceSpecification.getNominalCPUThroughout(unit) * numberOfSensorElements;
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

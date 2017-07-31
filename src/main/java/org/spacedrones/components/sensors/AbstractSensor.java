package org.spacedrones.components.sensors;

import java.util.List;

import org.spacedrones.Configuration;
import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractSensor extends AbstractBusComponent implements Sensor {

	protected SensorProfile sensorProfile;

	AbstractSensor(String name, BusComponentSpecification busResourceSpecification,
								 SensorProfile sensorProfile) {
		super(name, busResourceSpecification);
		this.sensorProfile = sensorProfile;
	}

	
	@Override
	public double getSensorGain() {
		return sensorProfile.getSignalGain();
	}


	@Override
	public double getSensorThreshold() {
		return sensorProfile.getSignalThreshold();
	}


	@Override
	public List<SensorResult> activeScan(double duration, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {
		
		String spacecraftIdent = (String)(this.getSystemComputer().getSystemData("spaceraft-ident"));
		return activeScan(spacecraftIdent, duration, signalStrength, propagationModel, sensorType);
	}


	private List<SensorResult> activeScan(String spacecraftIdent, double duration, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {

		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.activeScan(spacecraftIdent, duration, signalStrength, propagationModel, sensorProfile);
	}

	@Override
	public List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile) {
		String spacecraftIdent = (String)(this.getSystemComputer().getSystemData("spaceraft-ident"));
		return passiveScan(spacecraftIdent, duration, sensorProfile);
	}


	private List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile) {
		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.passiveScan(spacecraftIdent, duration, sensorProfile);
	}


	@Override
	public SensorProfile getSensorProfile() {
		return sensorProfile;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void tick() {
	}

}

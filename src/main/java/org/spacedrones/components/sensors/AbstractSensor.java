package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.spacecraft.BusComponentSpecification;

import java.util.List;

public abstract class AbstractSensor extends AbstractBusComponent implements Sensor {

	final private SensorProfile sensorProfile;

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
		return activeScan(getSystemComputer().getVesselIdent(), duration, signalStrength, propagationModel, sensorType);
	}

	private List<SensorResult> activeScan(String spacecraftIdent, double duration, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {

		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.activeScan(spacecraftIdent, duration, signalStrength, propagationModel, sensorProfile);
	}

	@Override
	public final List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile) {
		return passiveScan(getIdent(), duration, sensorProfile);
	}

	private List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile) {
		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator();
		return sensorResponseMediator.passiveScan(spacecraftIdent, duration, sensorProfile);
	}

  @Override
  public String getIdent() {
    return getSystemComputer().id();
  }

	@Override
	public SensorProfile getSensorProfile() {
		return sensorProfile;
	}
	
	@Override
	public void tick(double dt) {
	}

}

package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.*;

import java.util.List;

public abstract class AbstractSensor extends AbstractBusComponent implements Sensor {

	final private EMSensorProfile sensorProfile;

	AbstractSensor(String name, BusComponentSpecification busResourceSpecification,
								 EMSensorProfile sensorProfile) {
		super(name, busResourceSpecification);
		this.sensorProfile = sensorProfile;
	}

	@Override
	public double getSensorGain() {
		return sensorProfile.getSignalGain();
	}

	@Override
	public EMSensorThreshold getSensorThreshold() {
		return sensorProfile.getSignalThreshold();
	}

  @Override
  public List<SensorResult> activeScan(double duration, double signalStrength,
			SignalPropagationModel propagationModel, int sensorType) {

		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator(getIdent());
		return sensorResponseMediator.activeScan(duration, signalStrength, propagationModel, sensorProfile);
	}

  @Override
  public List<SensorResult> passiveScan(double duration, EMSensorProfile sensorProfile) {
		SensorResponseMediator sensorResponseMediator = Configuration.getSensorResponseMediator(getIdent());
		return sensorResponseMediator.passiveScan(duration, sensorProfile);
	}

  @Override
  public String getIdent() {
    return isRegisteredWithSystemComputer() ? getSystemComputer().id() : id();
  }

	@Override
	public EMSensorProfile getSensorProfile() {
		return sensorProfile;
	}

	@Override
	public void tick(double dt) {
	}

  @Override
  public SystemStatus runDiagnostics(int level) {
    return null;
  }
}

package org.spacedrones.components.sensors;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.universe.UniverseAware;

import java.util.List;

public interface Sensor extends SpacecraftBusComponent, UniverseAware {

	SensorProfile getSensorProfile();

	double getSensorGain();

	SensorThreshold getSensorThreshold();

	List activeScan(double duration, double signalStrength, SignalPropagationModel propagationModel, int sensorType);

	List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile);

}

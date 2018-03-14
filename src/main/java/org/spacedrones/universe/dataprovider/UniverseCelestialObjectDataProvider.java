package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.sensors.SensorProfile;

public interface UniverseCelestialObjectDataProvider {
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}

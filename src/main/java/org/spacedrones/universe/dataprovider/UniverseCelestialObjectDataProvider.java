package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.sensors.EMSensorProfile;

public interface UniverseCelestialObjectDataProvider {
	double getSignalPropagationSpeed(EMSensorProfile sensorProfile);
	void populate();
}

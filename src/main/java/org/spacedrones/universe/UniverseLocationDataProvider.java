package org.spacedrones.universe;

import java.math.BigDecimal;
import java.util.List;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.universe.celestialobjects.CelestialObject;

public interface UniverseLocationDataProvider {
	int addLocation(CelestialObject location);
	CelestialObject getLocationById(String locationID);
	CelestialObject getLocationByName(String locationProperName);
	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsByCategory(TypeInfo category);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}

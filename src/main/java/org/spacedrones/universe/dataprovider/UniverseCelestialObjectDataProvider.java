package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;
import java.util.List;

public interface UniverseCelestialObjectDataProvider {
	void addCelestialObject(String name, CelestialObject celestialObject, Coordinates coordinates);
	CelestialObject getCelestialObjectById(String celestialObjectID);
	CelestialObject getCelestialObjectByName(String celestialObjectName);

	Coordinates getCelestialObjectCoordinatesById(String celestialObjectID);
	Coordinates getCelestialObjectCoordinatesByName(String celestialObjectName);

	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsByCategory(TypeInfo category);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getCelestialObjectByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}

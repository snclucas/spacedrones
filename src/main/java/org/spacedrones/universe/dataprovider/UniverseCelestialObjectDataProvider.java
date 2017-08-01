package org.spacedrones.universe.dataprovider;

import java.math.BigDecimal;
import java.util.List;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;
import org.spacedrones.universe.celestialobjects.CelestialObject;

public interface UniverseCelestialObjectDataProvider {
	void addCelestialObject(CelestialObject celestialObject, Coordinates coordinates);
	CelestialObject getCelestialObjectById(String celestialObjectID);
	CelestialObject getCelestialObjectByName(String celestialObjectName);

	Coordinates getCelestialObjectCoordinatesById(String celestialObjectID);
	Coordinates getCelestialObjectCoordinatesByName(String celestialObjectName);

	List<CelestialObject> getLocationsByType(TypeInfo type);
	List<CelestialObject> getLocationsByCategory(TypeInfo category);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance);
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}

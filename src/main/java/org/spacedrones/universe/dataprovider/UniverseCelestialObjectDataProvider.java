package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.*;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.GalacticLocation;
import org.spacedrones.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;
import java.util.List;

public interface UniverseCelestialObjectDataProvider {
	void addCelestialObject(String name, CelestialObject celestialObject, GalacticLocation location);
	CelestialObject getCelestialObjectById(String celestialObjectID);
	CelestialObject getCelestialObjectByName(String celestialObjectName);

	//void setRelativeVelocity(String celestialObjectID, double[] velocity, CelestialObject relativeTo);
 // double[] getRelativeVelocity(String celestialObjectID);

	GalacticLocation getCelestialObjectLocationById(String celestialObjectID);
	GalacticLocation getCelestialObjectLocationByName(String celestialObjectName);

	List<CelestialObject> getLocationsByType(Class<? extends CelestialObject> type);
	List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
	List<CelestialObject> getCelestialObjectByTypeCloserThan(Class<? extends CelestialObject> type, GalacticLocation localtion, BigDecimal distance);
	double getSignalPropagationSpeed(SensorProfile sensorProfile);
	void populate();
}

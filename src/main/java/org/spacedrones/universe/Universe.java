package org.spacedrones.universe;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;
import org.spacedrones.components.Tickable;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.dataprovider.SpacecraftDataProvider;
import org.spacedrones.universe.dataprovider.UniverseCelestialObjectDataProvider;

import java.math.BigDecimal;
import java.util.*;

public class Universe implements UniverseCelestialObjectDataProvider,
		SpacecraftDataProvider, EnvironmentDataProvider, Tickable {

	private UniverseCelestialObjectDataProvider universeLocationDataProvider = Configuration.getUniverseLocationDataProvider();
	private SpacecraftDataProvider spacecraftDataProvider = Configuration.getUniverseSpacecraftLocationDataProvider();
	private EnvironmentDataProvider universeEnvironmentDataProvider = Configuration.getEnvironmentDataProvider();


	private static volatile Universe instance, emptyInstance;


	public static Universe getInstance() {
		Universe localInstance = instance;
		if(localInstance == null) {
			synchronized (Universe.class) {
				localInstance = instance;
				if(localInstance == null) {
					instance = localInstance = new Universe();
					instance.populate();
				}
			}
		}
		return localInstance;
	}

	public static Universe getEmptyInstance() {
		Universe localEmptyInstance = emptyInstance;
		if(localEmptyInstance == null) {
			synchronized (Universe.class) {
				localEmptyInstance = emptyInstance;
				if(localEmptyInstance == null) {
					emptyInstance = localEmptyInstance = new Universe();
				}
			}
		}
		return localEmptyInstance;
	}



	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates) {
		spacecraftDataProvider.addSpacecraft(spacecraft, coordinates);
	}

	public void addComponent(Identifiable object, Coordinates coordinates) {
		spacecraftDataProvider.addComponent(object, coordinates);
	}


	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		spacecraftDataProvider.updateSpacecraftLocation(spacecraftIdent, coordinates);
	}


	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		return spacecraftDataProvider.getSpacecraftLocation(spacecraftIdent);
	}

  @Override
  public Coordinates getObjectLocationInUniverse(final String ident) {
    return spacecraftDataProvider.getObjectLocationInUniverse(ident);
  }

  public List<CelestialObject> getLocationsByType(TypeInfo type) {
		return universeLocationDataProvider.getLocationsByType(type);
	}


	public void addCelestialObject(String name, CelestialObject celestialObject, GalacticLocation location) {
		universeLocationDataProvider.addCelestialObject(name, celestialObject, location);
	}

	public CelestialObject getCelestialObjectById(String locationID) {
		return universeLocationDataProvider.getCelestialObjectById(locationID);
	}

	public CelestialObject getCelestialObjectByName(String locationProperName) {
		return universeLocationDataProvider.getCelestialObjectByName(locationProperName);
	}

  @Override
  public GalacticLocation getCelestialObjectLocationById(String celestialObjectID) {
    return universeLocationDataProvider.getCelestialObjectLocationById(celestialObjectID);
  }

  @Override
  public GalacticLocation getCelestialObjectLocationByName(String celestialObjectName) {
    return universeLocationDataProvider.getCelestialObjectLocationByName(celestialObjectName);
  }


  @Override
	public Spacecraft getSpacecraftByIdent(String ident) {
		return spacecraftDataProvider.getSpacecraftByIdent(ident);
	}

	@Override
	public void tick(double dt) {
		spacecraftDataProvider.getAllSpacecraft()
						.forEach(sc -> sc.tick(dt));
	}

	//Delegate methods

	@Override
	public List<CelestialObject> getLocationsByCategory(TypeInfo category) {
		return universeLocationDataProvider.getLocationsByCategory(category);
	}


	@Override
	public List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance) {
		return universeLocationDataProvider.getLocationsCloserThan(coordinates, distance);
	}


	@Override
	public List<CelestialObject> getCelestialObjectByTypeCloserThan(TypeInfo type, GalacticLocation localtion, BigDecimal distance) {
		return universeLocationDataProvider.getCelestialObjectByTypeCloserThan(type, localtion, distance);
	}


	@Override
	public double getSignalPropagationSpeed(SensorProfile sensorProfile) {
		return universeLocationDataProvider.getSignalPropagationSpeed(sensorProfile);
	}


	@Override
	public void populate() {
		universeLocationDataProvider.populate();
	}


	@Override
	public List<Spacecraft> getAllSpacecraft() {
		return spacecraftDataProvider.getAllSpacecraft();
	}


	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
		return spacecraftDataProvider.getSpacecraftVelocity(spacecraftIdent);
	}


	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
		spacecraftDataProvider.updateSpacecraftVelocity(spacecraftIdent, velocity);
	}


	@Override
	public BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit) {
		return spacecraftDataProvider.getDistanceBetweenTwoSpacecraft(spacecraftIdent1, spacecraftIdent2, unit);
	}


	@Override
	public Map<String, Coordinates> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range) {
		return spacecraftDataProvider.getSpacecraftWithinRangeOfCoordinates(coordinates, range);
	}


	@Override
	public EnvironmentData getEnvironmentData(Coordinates coordinates) {
		return universeEnvironmentDataProvider.getEnvironmentData(coordinates);
	}


	@Override
	public double getSubspaceNoise(Coordinates coordinates) {
		return universeEnvironmentDataProvider.getSubspaceNoise(coordinates);
	}

	public static double getUniversalTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);

		//Change this;
		return ((year) + day/365.0 +
						(hour/(365*24.0)) +
						(minute/(365.0*24.0*60.0)) +
						(second/(365.0*86400.0)) +
						(millisecond/(365.0*86400000.0)));
	}

}




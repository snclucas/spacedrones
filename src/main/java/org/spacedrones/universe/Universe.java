package org.spacedrones.universe;

import org.spacedrones.Configuration;
import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.Tickable;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacedrones.universe.dataprovider.ObjectLocationDataProvider;
import org.spacedrones.universe.dataprovider.ObjectMeta;
import org.spacedrones.universe.dataprovider.SignalResponseProvider;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class Universe implements ObjectLocationDataProvider, EnvironmentDataProvider, Tickable, SignalResponseProvider {

	private ObjectLocationDataProvider spacecraftDataProvider = Configuration.getUniverseSpacecraftLocationDataProvider();
	private EnvironmentDataProvider universeEnvironmentDataProvider = Configuration.getEnvironmentDataProvider();
	private SignalResponseProvider signalResponseProvider = Configuration.getSignalResponseProvider();


	private static volatile Universe instance;


	public static Universe getInstance() {
		Universe localInstance = instance;
		if(localInstance == null) {
			synchronized (Universe.class) {
				localInstance = instance;
				if(localInstance == null) {
					instance = localInstance = new Universe();
					//instance.populate();
				}
			}
		}
		return localInstance;
	}


	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity) {
		spacecraftDataProvider.addSpacecraft(spacecraft, coordinates, velocity);
	}

	public void addObject(Taxonomic object, Coordinates coordinates, double[] velocity) {
		spacecraftDataProvider.addObject(object, coordinates, velocity);
	}


	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		spacecraftDataProvider.updateSpacecraftLocation(spacecraftIdent, coordinates);
	}


	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		return spacecraftDataProvider.getSpacecraftLocation(spacecraftIdent);
	}


	public void addCelestialObject(String name, CelestialObject celestialObject, Coordinates coordinates, double[] velocity) {
    spacecraftDataProvider.addCelestialObject(name, celestialObject, coordinates, velocity);
	}

	public Optional<CelestialObject> getCelestialObjectById(String locationID) {
		return spacecraftDataProvider.getCelestialObjectById(locationID);
	}

  @Override
  public Optional<Coordinates> getCelestialObjectLocationById(String celestialObjectID) {
    return spacecraftDataProvider.getCelestialObjectLocationById(celestialObjectID);
  }

	@Override
	public List<CelestialObject> getAllCelestialObjectsCloserThan(final Coordinates coordinates, final BigDecimal range, final Unit unit) {
		return spacecraftDataProvider.getAllCelestialObjectsCloserThan(coordinates, range, unit);
	}

  @Override
  public List<ObjectMeta<CelestialObject>> getAllCelestialObjectsCloserThanAsMeta(final Coordinates coordinates, final BigDecimal range, final Unit unit) {
    return spacecraftDataProvider.getAllCelestialObjectsCloserThanAsMeta(coordinates, range, unit);
  }

	@Override
	public Optional<Spacecraft> getSpacecraftById(String ident) {
		return spacecraftDataProvider.getSpacecraftById(ident);
	}

  @Override
  public <T extends Taxonomic> List<T> getAllObjectsByType(Class<T> type) {
    return spacecraftDataProvider.getAllObjectsByType(type);
  }

  @Override
  public <T> Optional<T> getObjectByIdAndType(String ident, Class<? extends Taxonomic> type) {
    return spacecraftDataProvider.getObjectByIdAndType(ident, type);
  }

  @Override
  public Optional<Coordinates> getObjectLocationById(final String ident) {
    return spacecraftDataProvider.getObjectLocationById(ident);
  }

  @Override
  public <T> Optional<T> getObjectById(String ident) {
    return spacecraftDataProvider.getObjectById(ident);
  }

  @Override
	public void tick(double dt) {
		spacecraftDataProvider.getAllSpacecraft()
						.forEach(sc -> sc.tick(dt));
	}

	//Delegate methods

	@Override
	public void populate() {
    spacecraftDataProvider.populate();
	}

  public void list() {
    spacecraftDataProvider.list();
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
	public List<Spacecraft> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range, Unit unit) {
		return spacecraftDataProvider.getSpacecraftWithinRangeOfCoordinates(coordinates, range, unit);
	}

	@Override
	public <T extends Taxonomic> List<T> getAllObjectsCloserThan(final Coordinates coordinates, final BigDecimal range, final Unit unit) {
		return spacecraftDataProvider.getAllObjectsCloserThan(coordinates, range, unit);
	}

	@Override
	public <T extends Taxonomic> List<T> getAllObjectsByTypeCloserThan(final Class<T> type, final Coordinates coordinates, final BigDecimal range, final Unit unit) {
		return spacecraftDataProvider.getAllObjectsByTypeCloserThan(type, coordinates, range, unit);
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

	@Override
	public SignalResponse getSignalResponse(final CelestialObject celestialObject, final SensorType sensorType, final BigDecimal distance) {
		return signalResponseProvider.getSignalResponse(celestialObject, sensorType, distance);
	}

	@Override
	public SignalResponse getSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, final SensorType sensorType, final BigDecimal distance) {
		return signalResponseProvider.getSignalResponse(sensorSignalResponseProfile, sensorType, distance);
	}
}




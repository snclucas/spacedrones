package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.*;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.physics.Constants;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.GalacticLocation;
import org.spacedrones.universe.celestialobjects.*;
import org.spacedrones.universe.structures.SubspaceBeacon;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class LocalUniverseLocationDataProvider extends AbstractUniverseDataProvider implements UniverseCelestialObjectDataProvider {

	private final Map<String, ObjectMeta> celestialObjects;
	private final Map<String, GalacticLocation> locations;
	private final Map<String, double[]> relativeVelocities;

  class	ObjectMeta {
  	public String id;
  	public String name;
    CelestialObject celestialObject;
    ObjectMeta(final String id, final String name, final CelestialObject celestialObject) {
      this.id = id;
      this.name = name;
      this.celestialObject = celestialObject;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      final ObjectMeta that = (ObjectMeta) o;
      return id.equals(that.id);
    }

    @Override
    public int hashCode() {
      int result = id.hashCode();
      result = 31 * result + name.hashCode();
      return result;
    }
  }

	public LocalUniverseLocationDataProvider() {
		celestialObjects = new HashMap<>();
		locations = new HashMap<>();
		relativeVelocities = new HashMap<>();
	}

	@Override
	public void addCelestialObject(String name, CelestialObject celestialObject, GalacticLocation location) {
		locations.put(celestialObject.id(), location);
    ObjectMeta meta = new ObjectMeta(celestialObject.id(), name, celestialObject);
		celestialObjects.put(celestialObject.id(), meta);
	}


	@Override
	public CelestialObject getCelestialObjectById(String celestialObjectID) {
    if(celestialObjects.containsKey(celestialObjectID)) {
      return celestialObjects.get(celestialObjectID).celestialObject;
    }
    else {
      return null;
    }
	}


	@Override
	public GalacticLocation getCelestialObjectLocationById(String celestialObjectID) {
		return locations.get(celestialObjectID);
	}


	@Override
	public CelestialObject getCelestialObjectByName(String locationProperName) {
		for (Entry<String, ObjectMeta> stringCelestialObjectEntry : celestialObjects.entrySet()) {
			CelestialObject co = stringCelestialObjectEntry.getValue().celestialObject;
			String name = stringCelestialObjectEntry.getValue().name;
			if (locationProperName.equals(name))
				return co;
		}
		return null;
	}

  //@Override
  //public void setRelativeVelocity(final String celestialObjectID, final double[] velocity, CelestialObject relativeTo) {
//
  //}

 // @Override
  //public double[] getRelativeVelocity(final String celestialObjectID, CelestialObject relativeTo) {
 //   return relativeVelocities.get(celestialObjectID);
 // }

  private double[] getRelativeVelocity(final String celestialObjectID) {
    return relativeVelocities.get(celestialObjectID);
  }


  @Override
	public GalacticLocation getCelestialObjectLocationByName(String celestialObjectName) {
    for (Entry<String, ObjectMeta> me : celestialObjects.entrySet()) {
      String name = me.getValue().name;
      if (celestialObjectName.equals(name))
        return locations.get(me.getKey());
    }
		return null;
	}

	@Override
	public List<CelestialObject> getLocationsByType(Class<? extends CelestialObject> type) {
		List<CelestialObject> locations = new ArrayList<>();

    for (Entry<String, ObjectMeta> stringCelestialObjectEntry : celestialObjects.entrySet()) {
      CelestialObject celObj = stringCelestialObjectEntry.getValue().celestialObject;
      if (type == (celObj.getClass()))
        locations.add(celObj);
    }
		return locations;
	}

	@Override
	public List<CelestialObject> getCelestialObjectByTypeCloserThan(Class<? extends CelestialObject> type, GalacticLocation location, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<>();

    for (Entry<String, ObjectMeta> me : celestialObjects.entrySet()) {
      CelestialObject celestialObject = me.getValue().celestialObject;
      if (type == (celestialObject.getClass())) {
        Coordinates coords = this.locations.get(me.getKey()).getCoordinates();
        if (Utils.distanceToLocation(coords, location.getCoordinates(), Unit.One).compareTo(distance) <= 0)
          locations.add(celestialObject);
      }
    }
		return locations;
	}



	@Override
	public List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<>();
    for (Entry<String, ObjectMeta> me : celestialObjects.entrySet()) {
      CelestialObject celestialObject = me.getValue().celestialObject;
      Coordinates coords = this.locations.get(me.getKey()).getCoordinates();
      if (Utils.distanceToLocation(coords, coordinates, Unit.One).compareTo(distance) <= 0)
        locations.add(celestialObject);
    }
		return locations;
	}



	public void populate() {

    Coordinates galacticCenterCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
    CelestialObject galacticCenter
            = new Region(new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());
		GalacticLocation location = new GalacticLocation("Galactic center", galacticCenterCoordinates);
    addCelestialObject("Galactic center", galacticCenter, location);


    Coordinates solCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
		Star sol = new Star(StarClass.G,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));
		GalacticLocation solLocation = new GalacticLocation("Sol", solCoordinates);

		addCelestialObject("Sol", sol, solLocation);

		Coordinates alphaCenturiCoordinates =
            new Coordinates(
                    new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
                    new BigDecimal(2.83* Unit.Ly.value()),
                    new BigDecimal(101.34*Unit.Ly.value()));

		Star alphaCenturi = new Star(StarClass.G,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.O));
		GalacticLocation alphaLocation = new GalacticLocation("Sol", alphaCenturiCoordinates);

		addCelestialObject("Alpha centuri", alphaCenturi, alphaLocation);


		//Setup subspace beacons
		Coordinates c1 = solCoordinates.add(new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km.value())));
		GalacticLocation c1Location = new GalacticLocation("SolBeacon", c1);

		//Above Sol north pole, 1e8 Km
		addCelestialObject("SolBeacon",
						new SubspaceBeacon(
						        SensorSignalResponseLibrary
                            .getStandardSignalResponseProfileForObjectType(
                                    SensorSignalResponseLibrary.SUBSPACE_BEACON)), c1Location);

		Coordinates c2 = alphaCenturiCoordinates.add(
		        new Coordinates(
		                new BigDecimal(0.0),
                    new BigDecimal(0.0),
                    new BigDecimal(1e8*Unit.Km.value())));
		GalacticLocation c2Location = new GalacticLocation("ACBeacon", c2);

		addCelestialObject("ACBeacon",
            new SubspaceBeacon(SensorSignalResponseLibrary
                    .getStandardSignalResponseProfileForObjectType(
                            SensorSignalResponseLibrary.SUBSPACE_BEACON)), c2Location);
	}



	public double getSignalPropagationSpeed(SensorProfile sensorProfile) {
		SensorType sensorType = sensorProfile.getSensorType();
		if(sensorType == SensorType.OPTICAL) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == SensorType.RADAR) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == SensorType.GRAVIMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == SensorType.MAGNETOMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == SensorType.GRAVIMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == SensorType.SUBSPACE_RESONANCE) {
			return 100000.0 * Constants.c;
		}
		else
			return 0.0;
	}





}

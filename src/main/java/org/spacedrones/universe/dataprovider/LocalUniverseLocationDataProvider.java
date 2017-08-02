package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.physics.Constants;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
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
	
	private final Map<String, CelestialObject> celestialObjects;
	private final Map<String, Coordinates> celestialObjectLocations;

	public LocalUniverseLocationDataProvider() {
		super();
		celestialObjects = new HashMap<>();
		celestialObjectLocations = new HashMap<>();
	}

	@Override
	public void addCelestialObject(CelestialObject celestialObject, Coordinates coordinates) {
		celestialObjectLocations.put(celestialObject.getIdent(), coordinates);
		celestialObjects.put(celestialObject.getIdent(), celestialObject);
	}


	@Override
	public CelestialObject getCelestialObjectById(String celestialObjectID) {
		return celestialObjects.get(celestialObjectID);
	}


	@Override
	public Coordinates getCelestialObjectCoordinatesById(String celestialObjectID) {
		return celestialObjectLocations.get(celestialObjectID);
	}
	

	@Override
	public CelestialObject getCelestialObjectByName(String locationProperName) {
		for (Entry<String, CelestialObject> stringCelestialObjectEntry : celestialObjects.entrySet()) {
			CelestialObject co = stringCelestialObjectEntry.getValue();
			if (locationProperName.equals(co.getName()))
				return co;
		}
		return null;
	}


	@Override
	public Coordinates getCelestialObjectCoordinatesByName(String celestialObjectName) {
    for (Entry<String, CelestialObject> me : celestialObjects.entrySet()) {
      CelestialObject co = me.getValue();
      if (celestialObjectName.equals(co.getName()))
        return celestialObjectLocations.get(me.getKey());
    }
		return null;
	}
	

	@Override
	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		List<CelestialObject> locations = new ArrayList<>();

    for (Entry<String, CelestialObject> stringCelestialObjectEntry : celestialObjects.entrySet()) {
      CelestialObject loc = stringCelestialObjectEntry.getValue();
      if (type == (loc.getType()))
        locations.add(loc);
    }
		return locations;
	}
	
	
	@Override
	public List<CelestialObject> getLocationsByCategory(TypeInfo category) {
		return celestialObjects.entrySet().stream()
				.map(Entry::getValue)
				.filter(map -> map.getCategory().equals(category))
				.collect(Collectors.toList());
	}
	
	
	@Override
	public List<CelestialObject> getCelestialObjectByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<>();

    for (Entry<String, CelestialObject> me : celestialObjects.entrySet()) {
      CelestialObject celestialObject = me.getValue();
      if (type == (celestialObject.getType())) {
        Coordinates coords = celestialObjectLocations.get(me.getKey());
        if (Utils.distanceToLocation(coords, coordinates, Unit.One).compareTo(distance) <= 0)
          locations.add(celestialObject);
      }
    }
		return locations;
	}
	
	

	@Override
	public List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<>();
    for (Entry<String, CelestialObject> me : celestialObjects.entrySet()) {
      CelestialObject celestialObject = me.getValue();
      Coordinates coords = celestialObjectLocations.get(me.getKey());
      if (Utils.distanceToLocation(coords, coordinates, Unit.One).compareTo(distance) <= 0)
        locations.add(celestialObject);
    }
		return locations;
	}



	public void populate() {

    Coordinates galacticCenterCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
    CelestialObject galacticCenter
            = new Region("Galactic center",
            new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());

    addCelestialObject(galacticCenter, galacticCenterCoordinates);


    Coordinates solCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
		Star sol = new Star("Sol", Star.G_CLASS_STAR,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));

		addCelestialObject(sol, solCoordinates);

		Coordinates alphaCenturiCoordinates =
            new Coordinates(
                    new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
                    new BigDecimal(2.83* Unit.Ly.value()),
                    new BigDecimal(101.34*Unit.Ly.value()));

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.O_CLASS_STAR));

		addCelestialObject(alphaCenturi, alphaCenturiCoordinates);
		

		//Setup subspace beacons
		Coordinates c1 = solCoordinates.add(new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km.value())));
		//Above Sol north pole, 1e8 Km
		addCelestialObject(new SubspaceBeacon("SolBeacon",
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)), c1);

		Coordinates c2 = alphaCenturiCoordinates.add(
		        new Coordinates(
		                new BigDecimal(0.0),
                    new BigDecimal(0.0),
                    new BigDecimal(1e8*Unit.Km.value())));
		addCelestialObject(new SubspaceBeacon("ACBeacon", SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)), c2);
	}


	
	public double getSignalPropagationSpeed(SensorProfile sensorProfile) {
		TypeInfo sensorType = sensorProfile.getSensorType();
		if(sensorType == Sensor.OPTICAL) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == Sensor.RADAR) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == Sensor.GRAVIMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == Sensor.MAGNETOMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == Sensor.GRAVIMETRIC) {
			return 1.0 * Constants.c;
		}
		else if(sensorType == Sensor.SUBSPACE_RESONANCE) {
			return 100000.0 * Constants.c;
		}
		else
			return 0.0;
	}
	
	
	
	

}

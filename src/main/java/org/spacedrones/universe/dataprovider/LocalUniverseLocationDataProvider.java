package org.spacedrones.universe.dataprovider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.physics.Constants;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.structures.SubspaceBeacon;
import org.spacedrones.utils.Utils;

//XXX Make better

public class LocalUniverseLocationDataProvider extends AbstractUniverseDataProvider implements UniverseLocationDataProvider {
	
	private final Map<String, CelestialObject> celestialObjectLocations;

	public LocalUniverseLocationDataProvider() {
		super();
		celestialObjectLocations = new HashMap<String, CelestialObject>();
	}

	@Override
	public int addLocation(CelestialObject location) {
		return celestialObjectLocations.put(location.getIdent(), location) != null ? 1:0;
	}




	@Override
	public CelestialObject getLocationById(String locationID) {
		return celestialObjectLocations.get(locationID);
	}
	

	@Override
	public CelestialObject getLocationByName(String locationProperName) {
		Iterator<Entry<String, CelestialObject>> it = celestialObjectLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(locationProperName.equals(loc.getName()))
				return loc;
		}
		return null;
		
		
	}
	

	@Override
	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();

		Iterator<Entry<String, CelestialObject>> it = celestialObjectLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(type == (loc.getType()))
				locations.add(loc);
		}
		return locations;
	}
	
	
	@Override
	public List<CelestialObject> getLocationsByCategory(TypeInfo category) {
		
		
		List<CelestialObject> locations = new ArrayList<CelestialObject>();

		Iterator<Entry<String, CelestialObject>> it = celestialObjectLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(category == (loc.getCategory()))
				locations.add(loc);
		}
		return celestialObjectLocations.entrySet().stream()
				.map(x -> x.getValue())
				.filter(map -> map.getCategory().equals(category))
				.collect(Collectors.toList());
	}
	
	
	@Override
	public List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();

		Iterator<Entry<String, CelestialObject>> it = celestialObjectLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if(type == (loc.getType()))
				if( Utils.distanceToLocation(loc.getCoordinates(), coordinates, Unit.One).compareTo(distance) <= 0)
					locations.add(loc);
		}
		//return locations;
		
		
		return celestialObjectLocations.entrySet().stream()
				.map(x -> x.getValue())
				.filter(map -> map.getType().equals(type))
				.filter(map -> Utils.distanceToLocation(map.getCoordinates(), coordinates, Unit.One).compareTo(distance) <= 0)
				.collect(Collectors.toList());
		
	}
	
	

	@Override
	public List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance) {
		List<CelestialObject> locations = new ArrayList<CelestialObject>();
		Iterator<Entry<String, CelestialObject>> it = celestialObjectLocations.entrySet().iterator();
		while (it.hasNext()) {
			CelestialObject loc = it.next().getValue();
			if( Utils.distanceToLocation(loc.getCoordinates(), coordinates, Unit.One).compareTo(distance) <= 0)
				locations.add(loc);
		}
		return locations;
	}



	public void populate() {
		Star sol = new Star("Sol", Star.G_CLASS_STAR, new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,  
				new Coordinates(new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),new BigDecimal(2.83* Unit.Ly.value()),new BigDecimal(101.34*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.O_CLASS_STAR));
		addLocation(alphaCenturi);
		
		
		
		//Setup subspace beacons
		
		//Above Sol north pole, 1e8 Km
		addLocation(new SubspaceBeacon("SolBeacon", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km.value())), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));
		
		addLocation(new SubspaceBeacon("ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km.value())), alphaCenturi,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));
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

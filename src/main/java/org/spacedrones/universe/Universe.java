package org.spacedrones.universe;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.Tickable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.components.sensors.SensorProfile;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.Region;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.dataprovider.UniverseLocationDataProvider;
import org.spacedrones.universe.dataprovider.UniverseSpacecraftLocationDataProvider;
import org.spacedrones.universe.structures.SubspaceBeacon;

public class Universe implements UniverseLocationDataProvider,
		UniverseSpacecraftLocationDataProvider, EnvironmentDataProvider, Tickable {

	private UniverseLocationDataProvider universeLocationDataProvider = Configuration.getUniverseLocationDataProvider();
	private UniverseSpacecraftLocationDataProvider universeSpacecraftLocationDataProvider = Configuration.getUniverseSpacecraftLocationDataProvider();
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

	public final static CelestialObject galacticCenter 
	= new Region("Galactic center", new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0)),
			new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());


	public Universe() {
		super();
	}


	public List<CelestialObject> getCelelestialObjects() {
		return universeLocationDataProvider.getLocationsByCategory(CelestialObject.category());
	}


	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates) {
		universeSpacecraftLocationDataProvider.addSpacecraft(spacecraft, coordinates);
	}


	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		universeSpacecraftLocationDataProvider.updateSpacecraftLocation(spacecraftIdent, coordinates);
	}


	public void updateSpacecraftLocation(String spacecraftIdent, Location location) {
		universeSpacecraftLocationDataProvider.updateSpacecraftLocation(spacecraftIdent, location.getCoordinates());
	}


	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		return universeSpacecraftLocationDataProvider.getSpacecraftLocation(spacecraftIdent);
	}




	public void setupSimpleUniverse() {
		System.out.println("Adding ");
		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(
				new BigDecimal(8*Unit.kPc.value()),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		addLocation(sol);

		Star alphaCenturi = new Star("Alpha centuri", Star.G_CLASS_STAR,  
				new Coordinates(
						new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
						new BigDecimal(2.83* Unit.Ly.value()),
						new BigDecimal(101.34*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.M_CLASS_STAR));
		addLocation(alphaCenturi);



		//Setup subspace beacons

		//Above Sol north pole, 1e8 Km
		addLocation(new SubspaceBeacon("SolBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), sol,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));


		addLocation(new SubspaceBeacon("ACBeacon", 
				new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1*Unit.AU.value())), alphaCenturi,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON)));

	}







	public UniverseLocationDataProvider getDataProvider() {
		return universeLocationDataProvider;
	}

	public void setDataProvider(UniverseLocationDataProvider dataProvider) {
		this.universeLocationDataProvider = dataProvider;
	}


	public List<CelestialObject> getLocationsByType(TypeInfo type) {
		return universeLocationDataProvider.getLocationsByType(type);
	}


	public int addLocation(CelestialObject location) {
		return universeLocationDataProvider.addLocation(location);
	}

	public CelestialObject getLocationById(String locationID) {
		return universeLocationDataProvider.getLocationById(locationID);
	}

	public CelestialObject getLocationByName(String locationProperName) {
		return universeLocationDataProvider.getLocationByName(locationProperName);
	}



	public long getUniversalTime() {
		return System.currentTimeMillis();
	}





	@Override
	public void tick() {
		List<Spacecraft> col = universeSpacecraftLocationDataProvider.getSpacecraft()
				.entrySet().stream()
				.map(x -> x.getValue()).collect(Collectors.toList());
		col.stream().forEach(Spacecraft::tick);

		//Move the spacecraft
		for(Spacecraft spacecraft : universeSpacecraftLocationDataProvider.getSpacecraft().values()) {
			spacecraft.tick();
			Coordinates currentLocation = universeSpacecraftLocationDataProvider.getSpacecraftLocation(spacecraft.getIdent());
			double[] currentVelocity = universeSpacecraftLocationDataProvider.getSpacecraftVelocity(spacecraft.getIdent());

			double[] dV = new double[]{0.0, 0.0, 0.0};
			List<SpacecraftBusComponent> components = spacecraft.getSpacecraftBus().findComponentByType(ThrustingEngine.type());
			for(SpacecraftBusComponent component : components) {
				double[] thrust = ((ThrustingEngine) component).getThrust(currentVelocity);
				dV[0] += thrust[0] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
				dV[1] += thrust[1] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
				dV[2] += thrust[2] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
			};
			double[] newVelocity = new double[]{
					currentVelocity[0] + dV[0],currentVelocity[1] + dV[1],currentVelocity[2] + dV[2]
			};
			
			double[] translation = new double[]{
					currentVelocity[0] * 1 * Unit.s.value(),currentVelocity[1] * 1 * Unit.s.value(),currentVelocity[2] * 1 * Unit.s.value()
			};
			
			currentLocation.addDistance(new BigDecimal[]{
					new BigDecimal(translation[0]),
					new BigDecimal(translation[1]),
					new BigDecimal(translation[2])
			});
			
			universeSpacecraftLocationDataProvider.updateSpacecraftLocation(spacecraft.getIdent(), currentLocation);
			universeSpacecraftLocationDataProvider.updateSpacecraftVelocity(spacecraft.getIdent(), newVelocity);
			
			
			System.out.println(currentLocation);
		}

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
	public List<CelestialObject> getLocationsByTypeCloserThan(TypeInfo type, Coordinates coordinates, BigDecimal distance) {
		return universeLocationDataProvider.getLocationsByTypeCloserThan(type, coordinates, distance);
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
	public Map<String, Spacecraft> getSpacecraft() {
		return universeSpacecraftLocationDataProvider.getSpacecraft();
	}


	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
		return universeSpacecraftLocationDataProvider.getSpacecraftVelocity(spacecraftIdent);
	}


	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
		universeSpacecraftLocationDataProvider.updateSpacecraftVelocity(spacecraftIdent, velocity);
	}


	@Override
	public BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit) {
		return universeSpacecraftLocationDataProvider.getDistanceBetweenTwoSpacecraft(spacecraftIdent1, spacecraftIdent2, unit);
	}


	@Override
	public Map<String, Coordinates> getSpacecraftWithinRangeOfLocation(Location location, BigDecimal range) {
		return universeSpacecraftLocationDataProvider.getSpacecraftWithinRangeOfLocation(location, range);
	}


	@Override
	public EnvironmentData getEnvironmentData(Coordinates coordinates) {
		return universeEnvironmentDataProvider.getEnvironmentData(coordinates);
	}


	@Override
	public double getSubspaceNoise(Coordinates coordinates) {
		return universeEnvironmentDataProvider.getSubspaceNoise(coordinates);
	}

}




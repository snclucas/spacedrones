package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.UnknownObject;
import org.spacedrones.universe.dataprovider.UniverseCelestialObjectDataProvider;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LocalSensorResponseMediator implements SensorResponseMediator {
	private Universe universe = Universe.getInstance();
  private UniverseCelestialObjectDataProvider universeDataProvider = Configuration.getUniverseLocationDataProvider();
	EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();


	@Override
	public List<SensorResult> activeScan(String spacecraftIdent, double duration,
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile) {
		
		double signalPropagationSpeed = universeDataProvider.getSignalPropagationSpeed(sensorProfile);

		List<SensorResult> results = new ArrayList<>();
		Coordinates spacecraftLocation = universe.getSpacecraftLocation(spacecraftIdent);
		
		BigDecimal maximumDistanceScanned = new BigDecimal((duration * signalPropagationSpeed) / 2.0); // There and back
		
		List<CelestialObject> objects = 
				universeDataProvider.getLocationsCloserThan(spacecraftLocation, maximumDistanceScanned);

		return results; 
	}


	public List<SensorResult> passiveScan(String ident, double duration, SensorProfile sensorProfile) {
		List<SensorResult> results = new ArrayList<>();
		Coordinates spacecraftLocation = universe.getObjectLocationInUniverse(ident);
		
		BigDecimal maximumDistanceScanned = new BigDecimal(1000000 * Unit.Ly.value()); 
		
		List<CelestialObject> objectsWithinDistance =
				universeDataProvider.getLocationsCloserThan(spacecraftLocation, maximumDistanceScanned);

		for(CelestialObject object : objectsWithinDistance) {
			Coordinates coordinates = universe.getCelestialObjectCoordinatesById(object.getId());
			BigDecimal distance = Utils.distanceToLocation(coordinates, spacecraftLocation, Unit.One);
			
			SignalResponse returnedSignalResponse = object.getSignalResponse(sensorProfile.getSensorType(), distance);
			// TODO maybe set celestial object as UNKNOWN if under a certain threshold?

			System.out.println(object + " " + 
			returnedSignalResponse.getSignalStrength() + " " + 
					returnedSignalResponse.getSignalDispersion() + " " + 
			distance.doubleValue()/Unit.Ly.value());
			
			if(returnedSignalResponse.getSignalStrength() > 1.0) {
        object = new UnknownObject(object.getSensorSignalResponse());
      }

			SensorResult result = new SensorResult(object, Utils.distanceToLocation(coordinates, spacecraftLocation, Unit.One),
					Utils.vectorToLocation(coordinates, spacecraftLocation, false), returnedSignalResponse);
			results.add(result);
		}
		return results;
	}


}

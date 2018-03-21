package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.data.EnvironmentDataProvider;
import org.spacedrones.physics.Constants;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.UnknownObject;
import org.spacedrones.universe.dataprovider.ObjectMeta;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.*;

public class LocalSensorResponseMediator implements SensorResponseMediator {

	final private String id;
	private Universe universe = Universe.getInstance();
	EnvironmentDataProvider environmentDataProvider = Configuration.getEnvironmentDataProvider();

	public LocalSensorResponseMediator(String id) {
		this.id = id;
	}

	@Override
	public List<SensorResult> activeScan(double duration,
			double signalStrength, SignalPropagationModel propagationModel, EMSensorProfile sensorProfile) {

		double signalPropagationSpeed = getSignalPropagationSpeed(SensorType.OPTICAL);

		List<SensorResult> results = new ArrayList<>();
		Coordinates spacecraftLocation = universe.getSpacecraftLocation(id);

		BigDecimal maximumDistanceScanned = new BigDecimal((duration * signalPropagationSpeed) / 2.0); // There and back

		List<CelestialObject> objects =
				universe.getAllObjectsCloserThan(spacecraftLocation, maximumDistanceScanned, Unit.Ly);

		return results;
	}


	public List<SensorResult> passiveScan(double duration, EMSensorProfile sensorProfile) {
		List<SensorResult> results = new ArrayList<>();
		Coordinates spacecraftLocation = universe.getObjectLocationById(id).get();
		BigDecimal maximumDistanceScanned = new BigDecimal(1000000 * Unit.Ly.value());

		List<ObjectMeta<CelestialObject>> objectsWithinDistance =
            universe.getAllCelestialObjectsCloserThanAsMeta(spacecraftLocation, maximumDistanceScanned, Unit.Ly);

    Comparator<SensorResult> bySignal = (SensorResult o1, SensorResult o2) ->
            -Double.compare(
                    o1.getSignalResponse().getSignalStrength(), o2.getSignalResponse().getSignalStrength());


    for(ObjectMeta<CelestialObject> object : objectsWithinDistance) {
		  if(id.equals(object.object.id())) {
		    continue;
      }

			BigDecimal distance = Utils.distanceToLocation(object.coordinates, spacecraftLocation, Unit.One);
			SignalResponse returnedSignalResponse = universe.getEMSignalResponse(object.object, sensorProfile.getStdAppMagnitude(), distance);

			if(returnedSignalResponse.getSignalStrength() < sensorProfile.getSignalThreshold().getThresholdInWattsPerMeter()) {
        object.object = new UnknownObject(object.object.getSensorSignalResponse());
      }

			SensorResult result = new SensorResult(object.object, Utils.distanceToLocation(object.coordinates, spacecraftLocation, Unit.One),
					Utils.vectorToLocation(spacecraftLocation, object.coordinates, true), returnedSignalResponse);
			results.add(result);
		}
    results.sort(bySignal);
		return results;
	}

  public double getSignalPropagationSpeed(SensorType sensorType) {
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

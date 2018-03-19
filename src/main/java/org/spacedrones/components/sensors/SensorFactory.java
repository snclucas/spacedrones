package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;

import java.util.*;

public class SensorFactory {

  public final static String LinearSensorArray = LinearSensorArray.class.getSimpleName();
  public final static String FractalSensorArray = FractalSensorArray.class.getSimpleName();
  public final static String StarTracker = StarTracker.class.getSimpleName();


	public static Sensor getSensor(String sensorType, SensorType sensorSensingType, int numberOfSensorElements){
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

		if(Objects.equals(sensorType, LinearSensorArray)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);

			double gainPerElement = 10.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new LinearSensorArray(
					LinearSensorArray.class.getSimpleName(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		else if(Objects.equals(sensorType, FractalSensorArray)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);

			double gainPerElement = 100.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new FractalSensorArray(
					FractalSensorArray.class.getSimpleName(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		} else if(Objects.equals(sensorType, StarTracker)){

      SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);

      double gainPerElement = 100.0;
      double signalDetectionThreshold = -9;
      SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

      return new StarTracker(
              StarTracker.class.getSimpleName(), data.getBusComponentSpecification(), sensorProfile);
    }
		throw new IllegalArgumentException("Requested thingy now found");
	}

}

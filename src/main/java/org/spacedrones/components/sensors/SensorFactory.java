package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;

import java.util.*;

public class SensorFactory {

	public static Sensor getSensor(String sensorType, SensorType sensorSensingType, int numberOfSensorElements){
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

		if(Objects.equals(sensorType, LinearSensorArray.class.getSimpleName())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);

			double gainPerElement = 10.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new LinearSensorArray(
					LinearSensorArray.class.getSimpleName(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		else if(Objects.equals(sensorType, FractalSensorArray.class.getSimpleName())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);

			double gainPerElement = 100.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new FractalSensorArray(
					FractalSensorArray.class.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		return null;
	}

}

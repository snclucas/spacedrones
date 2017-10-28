package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;

public class SensorFactory {

	public static Sensor getSensor(TypeInfo sensorType, SensorType sensorSensingType, int numberOfSensorElements){
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		
		if(sensorType.equals(LinearSensorArray.type)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);
			
			double gainPerElement = 10.0;
			double signalDetectionThreshold = -9;	
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);
			
			return new LinearSensorArray(
					LinearSensorArray.type.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		else if(sensorType.equals(FractalSensorArray.type)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(sensorType);
			
			double gainPerElement = 100.0;
			double signalDetectionThreshold = -9;
			SensorProfile sensorProfile = new SensorProfile(sensorSensingType, signalDetectionThreshold, gainPerElement);

			return new FractalSensorArray(
					FractalSensorArray.type.toString(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);
		}
		return null;
	}

}

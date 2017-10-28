package org.spacedrones.components.sensors;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

import java.util.List;

public interface Sensor extends SpacecraftBusComponent {

	TypeInfo category = new TypeInfo("Sensor");
	
	SensorProfile getSensorProfile();
	
	double getSensorGain();
	
	double getSensorThreshold();
	
	List<SensorResult> activeScan(double duration, double signalStrength, SignalPropagationModel propagationModel, int sensorType);
	
	List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile);

}

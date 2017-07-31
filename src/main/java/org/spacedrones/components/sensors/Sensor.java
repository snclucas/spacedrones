package org.spacedrones.components.sensors;

import java.util.List;

import org.spacedrones.components.Executable;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface Sensor extends SpacecraftBusComponent, Executable {
	TypeInfo category = new TypeInfo("Sensor");
	
	SensorProfile getSensorProfile();
	
	TypeInfo OPTICAL = new TypeInfo("Optical");
	TypeInfo RADAR = new TypeInfo("Radar");
	TypeInfo GRAVIMETRIC = new TypeInfo("Gravimetric");
	TypeInfo MAGNETOMETRIC = new TypeInfo("Magnetometric");
	TypeInfo SUBSPACE_RESONANCE = new TypeInfo("Subspace resonance");
	
	double getSensorGain();
	
	double getSensorThreshold();
	
	List<SensorResult> activeScan(double duration, double signalStrength, SignalPropagationModel propagationModel, int sensorType);
	
	List<SensorResult> passiveScan(double duration, SensorProfile sensorProfile);
}

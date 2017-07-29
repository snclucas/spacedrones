package org.spacedrones.components.sensors;

import java.util.List;

public interface SensorResponseMediator {
	
	List<SensorResult> activeScan(String spacecraftIdent, double duration, 
			double signalStrength, SignalPropagationModel propagationModel, SensorProfile sensorProfile);
	
	List<SensorResult> passiveScan(String spacecraftIdent, double duration, SensorProfile sensorProfile);

}

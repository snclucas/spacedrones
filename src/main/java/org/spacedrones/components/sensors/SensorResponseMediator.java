package org.spacedrones.components.sensors;

import java.util.List;

public interface SensorResponseMediator {
	
	List<SensorResult> activeScan(double duration,
			double signalStrength, SignalPropagationModel propagationModel, EMSensorProfile sensorProfile);
	
	List<SensorResult> passiveScan(double duration, EMSensorProfile sensorProfile);

}

package org.spacedrones.components.sensors;

public class SensorProfile {

	private final SensorType sensorType;
	private final SensorThreshold signalThreshold;
	private final double signalGain;
	
	
	public SensorProfile(SensorType sensorType, SensorThreshold signalThreshold, double signalGain) {
		this.sensorType = sensorType;
		this.signalThreshold = signalThreshold;
		this.signalGain = signalGain;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	SensorThreshold getSignalThreshold() {
		return signalThreshold;
	}

	double getSignalGain() {
		return signalGain;
	}

}
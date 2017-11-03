package org.spacedrones.components.sensors;

public class SensorProfile {

	private final SensorType sensorType;
	private final double signalThreshold;
	private final double signalGain;
	
	
	public SensorProfile(SensorType sensorType, double signalThreshold, double signalGain) {
		this.sensorType = sensorType;
		this.signalThreshold = signalThreshold;
		this.signalGain = signalGain;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	double getSignalThreshold() {
		return signalThreshold;
	}

	double getSignalGain() {
		return signalGain;
	}

}
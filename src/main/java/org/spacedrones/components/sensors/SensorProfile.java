package org.spacedrones.components.sensors;

public class SensorProfile {

	private SensorType sensorType;
	private double signalThreshold;
	private double signalGain;
	
	
	public SensorProfile(SensorType sensorType, double signalThreshold,
			double signalGain) {
		super();
		this.sensorType = sensorType;
		this.signalThreshold = signalThreshold;
		this.signalGain = signalGain;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	double getSignalThreshold() {
		return signalThreshold;
	}

	void setSignalThreshold(double signalThreshold) {
		this.signalThreshold = signalThreshold;
	}

	double getSignalGain() {
		return signalGain;
	}

	void setSignalGain(double signalGain) {
		this.signalGain = signalGain;
	}

}

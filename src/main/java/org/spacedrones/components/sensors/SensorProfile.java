package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;


public class SensorProfile {

	private TypeInfo sensorType;
	private double signalThreshold;
	private double signalGain;
	
	
	public SensorProfile(TypeInfo sensorType, double signalThreshold,
			double signalGain) {
		super();
		this.sensorType = sensorType;
		this.signalThreshold = signalThreshold;
		this.signalGain = signalGain;
	}


	public TypeInfo getSensorType() {
		return sensorType;
	}


	public void setSensorType(TypeInfo sensorType) {
		this.sensorType = sensorType;
	}


	public double getSignalThreshold() {
		return signalThreshold;
	}


	public void setSignalThreshold(double signalThreshold) {
		this.signalThreshold = signalThreshold;
	}


	public double getSignalGain() {
		return signalGain;
	}


	public void setSignalGain(double signalGain) {
		this.signalGain = signalGain;
	}


}

package org.spacedrones.universe.celestialobjects;


public class BlackHole extends AbstractCelestialObject {

	private final double radius;

	public BlackHole(SensorSignalResponseProfile sensorSignalResponseProfile, double radius) {
		super(sensorSignalResponseProfile);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

}

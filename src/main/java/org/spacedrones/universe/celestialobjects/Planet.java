package org.spacedrones.universe.celestialobjects;


public class Planet extends AbstractCelestialObject {

	private final double radius;
	private final PlanetClass habitatClass;

	public Planet(SensorSignalResponseProfile sensorSignalResponseProfile,
								double radius, PlanetClass habitatClass) {
		super(sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	public double getRadius() {
		return radius;
	}

	public PlanetClass getHabitatClass() {
		return habitatClass;
	}

}

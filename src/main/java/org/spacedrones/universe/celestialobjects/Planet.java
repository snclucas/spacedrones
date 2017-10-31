package org.spacedrones.universe.celestialobjects;


import org.spacedrones.components.TypeInfo;

public class Planet extends AbstractCelestialObject {
	public static TypeInfo type = new TypeInfo("Planet");

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

  @Override
	public String describe() {
		return "Planet";
	}

}

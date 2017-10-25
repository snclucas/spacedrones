package org.spacedrones.universe.celestialobjects;


public class Planet extends AbstractCelestialObject {

	public static String HABITABLE_CLASS_M = "M";

	private double radius;
	private String habitatClass;

	public Planet(SensorSignalResponseProfile sensorSignalResponseProfile,
								double radius, String habitatClass) {
		super(sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getHabitatClass() {
		return habitatClass;
	}

	public void setHabitatClass(String habitatClass) {
		this.habitatClass = habitatClass;
	}

}

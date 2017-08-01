package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;

public class Planet extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Planet");
	}

	public static String HABITABLE_CLASS_M = "M";

	private double radius;
	private String habitatClass;

	public Planet(String name,
			SensorSignalResponseProfile sensorSignalResponseProfile, double radius, String habitatClass) {
		super(name, sensorSignalResponseProfile);
		this.radius = radius;
		this.habitatClass = habitatClass;
	}


	@Override
	public TypeInfo getType() {
		return type();
	}

  @Override
  public String getName() {
    return type().toString();
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

	@Override
	public String describe() {
		return "A planet is a large body orbiting one or more stars.";
	}

}

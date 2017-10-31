package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;

import java.util.List;


public class Star extends AbstractCelestialObject {
  public static TypeInfo type = new TypeInfo("Star");

	protected List<Asteroid> asteroids;
	protected List<Planet> planets;
	
	private final StarClass classification;

	public Star(StarClass classification, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
		this.classification = classification;
	}

	StarClass getClassification() {
		return classification;
	}

  @Override
  public String describe() {
    return "Star";
  }
}
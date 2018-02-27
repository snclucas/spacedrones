package org.spacedrones.universe.celestialobjects;

import java.util.List;


public class Star extends AbstractCelestialObject {

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

}
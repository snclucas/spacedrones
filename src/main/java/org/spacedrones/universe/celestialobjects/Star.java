package org.spacedrones.universe.celestialobjects;

import java.util.List;


public class Star extends AbstractCelestialObject {

	enum StarClass {

    O("O-class star"),
    B("B-class star"),
    A("A-class star"),
    F("F-class star"),
    G("G-class star"),
    K("K-class star"),
    M("M-class star");

		private final String starClass;

		private StarClass(String starClass) {
			this.starClass = starClass;
		}

		public String getStarClass() {
			return starClass;
		}

	}

	protected List<Asteroid> asteroids;
	protected List<Planet> planets;
	
	private String classification;

	public Star(String name, String classification, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, sensorSignalResponseProfile);
		this.classification = classification;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}

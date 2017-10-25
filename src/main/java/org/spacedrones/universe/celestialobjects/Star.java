package org.spacedrones.universe.celestialobjects;

import java.util.List;


public class Star extends AbstractCelestialObject {
	
	public final static String O_CLASS_STAR = "O-class star"; // e.g. 10 Lacertra
	public final static String B_CLASS_STAR = "B-class star"; // e.g. Rigel, SPica
	public final static String A_CLASS_STAR = "A-class star"; // e.g. Sirius, Vega
	public final static String F_CLASS_STAR = "F-class star"; // e.g. Canopus, Procyon
	public final static String G_CLASS_STAR = "G-class star"; // e.g. Sol
	public final static String K_CLASS_STAR = "K-class star"; // e.g. Arcturus, Alderbaran
	public final static String M_CLASS_STAR = "M-class star"; // e.g. Betegeuse
	

	protected List<Asteroid> asteroids;
	protected List<Planet> planets;
	
	private String classification;

	public Star(String classification, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
		this.classification = classification;
	}
	

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}

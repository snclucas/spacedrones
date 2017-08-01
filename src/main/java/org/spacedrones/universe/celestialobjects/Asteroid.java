package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;

public abstract class Asteroid extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Asteroid");
	}

	
	public Asteroid(String name, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, sensorSignalResponseProfile);
	}

	
	@Override
	public TypeInfo getType() {
		return type();
	}
}

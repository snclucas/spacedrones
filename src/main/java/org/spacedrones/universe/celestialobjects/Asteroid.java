package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;

public abstract class Asteroid extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Asteroid");
	}

	
	public Asteroid(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	
	public Asteroid(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}
	
	
	@Override
	public TypeInfo getType() {
		return type();
	}
}

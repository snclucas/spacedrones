package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;

public class UnknownObject extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("UnkownObject");
	}

	public UnknownObject(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}

	@Override
	public TypeInfo getType() {
		return type();
	}

	@Override
	public String describe() {
		return "An unknown object.";
	}

}

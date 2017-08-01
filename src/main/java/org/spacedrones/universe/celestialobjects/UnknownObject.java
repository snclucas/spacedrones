package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;

public class UnknownObject extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("UnkownObject");
	}

	public UnknownObject(String name, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, sensorSignalResponseProfile);
	}

	@Override
	public TypeInfo getType() {
		return type();
	}

	@Override
	public String getName() {
		return type().toString();
	}

	@Override
	public String describe() {
		return "An unknown object.";
	}

}

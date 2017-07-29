package org.spacedrones.universe.structures;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
	
	public static TypeInfo typeInfo = new TypeInfo("SubspaceBeacon");

	public SubspaceBeacon(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, sensorSignalResponseProfile);
	}
	
	public SubspaceBeacon(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates, relativeTo, sensorSignalResponseProfile);
	}

	@Override
	public TypeInfo getType() {
		return type();
	}

	
	public static TypeInfo type() {
		return typeInfo;
	}
	
	
	@Override
	public String describe() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}
}

package org.spacedrones.universe.structures;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
	
	public static TypeInfo typeInfo = new TypeInfo("SubspaceBeacon");

	public SubspaceBeacon(String name, SensorSignalResponseProfile sensorSignalResponseProfile) {
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


	public static TypeInfo type() {
		return typeInfo;
	}
	
	
	@Override
	public String describe() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}
}

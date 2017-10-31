package org.spacedrones.universe.structures;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
  public static TypeInfo type = new TypeInfo("CelestialObject");

	public SubspaceBeacon(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public String describe() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}
}
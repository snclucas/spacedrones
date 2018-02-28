package org.spacedrones.universe.structures;

import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {

	public SubspaceBeacon(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
	}

	@Override
	public String id() {
		return null;
	}

}
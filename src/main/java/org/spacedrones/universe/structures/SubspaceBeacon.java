package org.spacedrones.universe.structures;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject implements Identifiable{

	public SubspaceBeacon(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
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
	public String getIdent() {
		return null;
	}


	@Override
	public String describe() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}
}

package org.spacedrones.universe.structures;

import org.spacedrones.universe.celestialobjects.AbstractCelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

public class SubspaceBeacon extends AbstractCelestialObject {
  public static TypeInfo type = new TypeInfo("SubspaceBeacon");

	public SubspaceBeacon(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
	}

	@Override
	public String id() {
		return null;
	}

	@Override
	public String description() {
		return "A artifical structure designed to emit subspace signals to be used as a beacon.";
	}


  // ----- Taxonomy

  @Override
  public TypeInfo category() {
    return category;
  }

  @Override
  public TypeInfo type() {
    return new TypeInfo("SubspaceBeacon");
  }
}
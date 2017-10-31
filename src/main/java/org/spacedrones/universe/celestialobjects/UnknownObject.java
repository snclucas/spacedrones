package org.spacedrones.universe.celestialobjects;


import org.spacedrones.components.TypeInfo;

public class UnknownObject extends AbstractCelestialObject {
  public static TypeInfo type = new TypeInfo("UnknownObject");

	public UnknownObject(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
	}

  @Override
  public String describe() {
    return "UnknownObject";
  }

}

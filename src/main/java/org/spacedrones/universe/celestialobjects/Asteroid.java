package org.spacedrones.universe.celestialobjects;


import org.spacedrones.components.TypeInfo;

public class Asteroid extends AbstractCelestialObject {
	public static TypeInfo type = new TypeInfo("Asteroid");

	public Asteroid(SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(sensorSignalResponseProfile);
	}

  @Override
  public String describe() {
    return "Asteroid";
  }

}

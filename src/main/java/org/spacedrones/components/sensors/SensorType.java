package org.spacedrones.components.sensors;

public enum SensorType {
    OPTICAL("Optical"),
    RADAR("Radar"),
    GRAVIMETRIC("Gravimetric"),
    MAGNETOMETRIC("Magnetometric"),
    SUBSPACE_RESONANCE("Subspace resonance");

    SensorType(String typeName) {
      this.typeName = typeName;
    }

    private final String typeName;

    public String getType() {
      return typeName;
    }
}

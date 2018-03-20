package org.spacedrones.components.sensors;


import org.spacedrones.physics.Physics;
import org.spacedrones.physics.StdAppMagnitude;

public class EMSensorThreshold {

  final private double thresholdInWattsPerMeter;

  private EMSensorThreshold(final double thresholdInWattsPerMeter) {
    this.thresholdInWattsPerMeter = thresholdInWattsPerMeter;
  }

  public static EMSensorThreshold asWPerMeter(double thresholdInWattsPerMeter) {
    return new EMSensorThreshold(thresholdInWattsPerMeter);
  }

  public static EMSensorThreshold asMagnitude(double thresholdInMagnitude, StdAppMagnitude stdAppMagnitude) {
    double thresholdInWattsPerMeter = Physics.spectralFluxDensityPerMeter(thresholdInMagnitude, stdAppMagnitude);
    return new EMSensorThreshold(thresholdInWattsPerMeter);
  }

  public double getThresholdInWattsPerMeter() {
    return this.thresholdInWattsPerMeter;
  }

}

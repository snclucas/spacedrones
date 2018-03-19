package org.spacedrones.components.sensors;


import org.spacedrones.physics.Physics;

public class SensorThreshold {
  public enum Unit {
    IN_WATTS,
    IN_MAGNITUDE;
  }

  private Unit units;
  final private double threshold;

  private SensorThreshold(final double threshold, Unit units) {
    this.threshold = threshold;
    this.units = units;
  }

  public static SensorThreshold asW(double thresholdInWatts) {
    return new SensorThreshold(thresholdInWatts, Unit.IN_WATTS);
  }

  public static SensorThreshold asMagnitude(double thresholdInMagnitude) {
    return new SensorThreshold(thresholdInMagnitude,  Unit.IN_MAGNITUDE);
  }

  public double getThresholdInWatts() {
    return (units ==  Unit.IN_WATTS) ? this.threshold : Physics.absMag2LuminosityInW(threshold);
  }

  public double getThresholdInMagnitude() {
    return (units ==  Unit.IN_MAGNITUDE) ? this.threshold : Physics.luminosityInW2AbsMag(threshold);
  }

}

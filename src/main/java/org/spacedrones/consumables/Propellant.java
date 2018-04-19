package org.spacedrones.consumables;


public interface Propellant {

  String getName();

  double getAverageExhaustVelocity();

  double getChamberTemperature();

  double getCharacteristicVelocity();

  double getSpecificHeatRatio();

  double getBulkDensity();
}

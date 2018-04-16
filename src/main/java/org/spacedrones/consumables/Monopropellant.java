package org.spacedrones.consumables;


public class Monopropellant implements Propellant {

  private final String name;

  private final FuelConstituent propellant;

  private final double gamma;

  private final double averageExhaustVelocity;

  private final double chamberTemperature;

  private final double bulkDensity;

  private final double characteristicVelocity;


  public Monopropellant(final String name, final FuelConstituent propellant,
                        final double averageExhaustVelocity, final double chamberTemperature,
                        final double characteristicVelocity) {
    this.name = name;
    this.propellant = propellant;

    this.averageExhaustVelocity = averageExhaustVelocity;
    this.chamberTemperature = chamberTemperature;
    this.characteristicVelocity = characteristicVelocity;

    gamma = propellant.getGamma();

    bulkDensity = propellant.getDensity();
  }

  public FuelConstituent getPropellant() {
    return propellant;
  }

  public String getName() {
    return name;
  }

  public double getGamma() {
    return gamma;
  }

  public double getBulkDensity() {
    return bulkDensity;
  }

  public double getAverageExhaustVelocity() {
    return averageExhaustVelocity;
  }

  public double getChamberTemperature() {
    return chamberTemperature;
  }

  public double getCharacteristicVelocity() {
    return characteristicVelocity;
  }
}
package org.spacedrones.consumables;


public class Bipropellant implements Propellant{

  private final String name;

  private final FuelConstituent fuel;
  private final FuelConstituent oxidizer;

  private double fuelMassFraction;
  private double oxidizerMassFraction;

  private final double gamma;

  private final double averageExhaustVelocity;

  private final double mixtureRatio; //Mixture ratio: mass oxidizer / mass fuel

  private final double chamberTemperature;

  private final double bulkDensity;

  private final double characteristicVelocity;


  public void setMassFractions(final double mixtureRatio) {
    this.fuelMassFraction = 1/(mixtureRatio + 1);
    this.oxidizerMassFraction = 1 - this.fuelMassFraction;
  }

  public Bipropellant(final String name, final FuelConstituent fuel, final FuelConstituent oxidizer, final double mixtureRatio,
                      final double averageExhaustVelocity, final double chamberTemperature,
                      final double characteristicVelocity) {
    this.name = name;
    this.fuel = fuel;
    this.oxidizer = oxidizer;
    this.mixtureRatio = mixtureRatio;
    setMassFractions(mixtureRatio);
    this.averageExhaustVelocity = averageExhaustVelocity;
    this.chamberTemperature = chamberTemperature;
    this.characteristicVelocity = characteristicVelocity;

    gamma = getFuel().getGamma() * fuelMassFraction +
            getOxidizer().getGamma() * oxidizerMassFraction;

    bulkDensity = getFuel().getDensity() * fuelMassFraction +
            getOxidizer().getDensity() * oxidizerMassFraction;
  }

  public String getName() {
    return name;
  }

  public FuelConstituent getFuel() {
    return fuel;
  }

  public FuelConstituent getOxidizer() {
    return oxidizer;
  }

  public double getFuelMassFraction() {
    return fuelMassFraction;
  }

  public double getOxidizerMassFraction() {
    return oxidizerMassFraction;
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

  public double getMixtureRatio() {
    return mixtureRatio;
  }

  public double getChamberTemperature() {
    return chamberTemperature;
  }

  public double getCharacteristicVelocity() {
    return characteristicVelocity;
  }
}

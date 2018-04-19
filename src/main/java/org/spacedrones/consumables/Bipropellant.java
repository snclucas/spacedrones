package org.spacedrones.consumables;


import org.spacedrones.materials.Liquid;

public class Bipropellant implements Propellant{

  private final String name;

  private final Liquid fuel;
  private final Liquid oxidizer;

  private double fuelMassFraction;
  private double oxidizerMassFraction;

  private final double gamma;

  private final double averageExhaustVelocity;

  private final double mixtureRatio; //Mixture ratio: mass oxidizer / mass fluid

  private final double chamberTemperature;

  private final double bulkDensity;

  private final double characteristicVelocity;

  private final double Isp;


  public void setMassFractions(final double mixtureRatio) {
    this.fuelMassFraction = 1/(mixtureRatio + 1);
    this.oxidizerMassFraction = 1 - this.fuelMassFraction;
  }

  public Bipropellant(final String name, final Liquid fuel, final Liquid oxidizer, final double mixtureRatio,
                      final double averageExhaustVelocity, final double chamberTemperature,
                      final double characteristicVelocity, final double Isp) {
    this.name = name;
    this.fuel = fuel;
    this.oxidizer = oxidizer;
    this.mixtureRatio = mixtureRatio;
    setMassFractions(mixtureRatio);
    this.averageExhaustVelocity = averageExhaustVelocity;
    this.chamberTemperature = chamberTemperature;
    this.characteristicVelocity = characteristicVelocity;
    this.Isp = Isp;

    gamma = getFuel().getSpecificHeatRatio() * fuelMassFraction +
            getOxidizer().getSpecificHeatRatio() * oxidizerMassFraction;

    bulkDensity = getFuel().getDensity() * fuelMassFraction +
            getOxidizer().getDensity() * oxidizerMassFraction;
  }


  public String getName() {
    return name;
  }

  public Liquid getFuel() {
    return fuel;
  }

  public Liquid getOxidizer() {
    return oxidizer;
  }

  public double getFuelMassFraction() {
    return fuelMassFraction;
  }

  public double getOxidizerMassFraction() {
    return oxidizerMassFraction;
  }

  public double getSpecificHeatRatio() {
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

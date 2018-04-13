package org.spacedrones.consumables;


public class FuelOxMix {

  private final FuelConstituent fuel;
  private final FuelConstituent oxidizer;

  private final double fuelMass;
  private final double oxidizerMass;

  private final double gamma;


  public FuelOxMix(final FuelConstituent fuel, final double fuelMass, final FuelConstituent oxidizer, final double oxidizerMass) {
    this.fuel = fuel;
    this.oxidizer = oxidizer;
    this.fuelMass = fuelMass;
    this.oxidizerMass = oxidizerMass;

    gamma = getFuel().getGamma() * getFuelMass() / (getFuelMass() + getOxidizerMass()) +
            getOxidizer().getGamma() * getOxidizerMass() / (getFuelMass() + getOxidizerMass());
  }

  public FuelConstituent getFuel() {
    return fuel;
  }

  public FuelConstituent getOxidizer() {
    return oxidizer;
  }

  public double getFuelMass() {
    return fuelMass;
  }

  public double getOxidizerMass() {
    return oxidizerMass;
  }

  public double getGamma() {
    return gamma;
  }

}

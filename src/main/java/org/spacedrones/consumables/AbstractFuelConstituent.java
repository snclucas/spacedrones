package org.spacedrones.consumables;


public class AbstractFuelConstituent implements FuelConstituent {

  private final String symbol;
  private final double density;
  private final double gamma;
  private final double molecularWeight;

  public AbstractFuelConstituent(String symbol, double gamma, double density, double molecularWeight) {
    this.symbol = symbol;
    this.gamma = gamma;
    this.density = density;
    this.molecularWeight = molecularWeight;
  }

  public String getSymbol() {
    return symbol;
  }

  public double getDensity() {
    return this.density;
  }

  public double getGamma() {
    return gamma;
  }

  public double getMolecularWeight() {
    return molecularWeight;
  }

}
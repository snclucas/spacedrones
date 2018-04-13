package org.spacedrones.consumables;

public class Fuel extends AbstractFuelConstituent {

  public static int LIQUID_XENON = "LiquidXenon".hashCode();
  public static int LIQUID_HYDROGEN = "LiquidHydrogen".hashCode();
  public static int HYDRAZINE = "Hydrazine".hashCode();


  public Fuel(final String symbol, final double gamma, final double density, final double molecularWeight) {
    super(symbol, gamma, density, molecularWeight);
  }
}

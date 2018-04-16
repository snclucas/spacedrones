package org.spacedrones.consumables;

public class Fuel extends AbstractFuelConstituent {

  public static int LIQUID_XENON = "LiquidXenon".hashCode();
  public static int LIQUID_HYDROGEN = "LiquidHydrogen".hashCode();


  public static int LIQUID_METHANE = "LiquidMethane".hashCode();
  public static int LIQUID_ETHANE = "LiquidEthane".hashCode();



  public Fuel(final String symbol, final double gamma, final double density, final double molecularWeight) {
    super(symbol, gamma, density, molecularWeight);
  }
}

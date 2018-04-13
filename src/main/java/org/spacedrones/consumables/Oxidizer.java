package org.spacedrones.consumables;


public class Oxidizer extends AbstractFuelConstituent  {

  public static int AIR = "Hydrazine".hashCode();
  public static int LIQUID_OXYGEN = "LiquidOxygen".hashCode();

  public Oxidizer(final String symbol, final double gamma, final double density, final double molecularWeight) {
    super(symbol, gamma, density, molecularWeight);
  }
}

package org.spacedrones.materials;


public class Liquid extends AbstractSubstance implements Fluid {

  public static int LIQUID_XENON = "LiquidXenon".hashCode();

  public static int LIQUID_HYDROGEN = "LiquidHydrogen".hashCode();

  public static int HYDRAZINE = "Hydrazine".hashCode();

  public static int LIQUID_METHANE = "LiquidMethane".hashCode();
  public static int LIQUID_ETHANE = "LiquidEthane".hashCode();

  public Liquid(final String name, final double density, final double specificHeatRatio, final double molecularWeight) {
    super(name, density, specificHeatRatio, molecularWeight);
  }
}

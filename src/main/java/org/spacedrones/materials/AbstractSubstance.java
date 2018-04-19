package org.spacedrones.materials;


public class AbstractSubstance implements Substance {

  private final double density;
  private final double specificHeatRatio;
  private final double molecularWeight;


  public AbstractSubstance(final String name, final double density, final double specificHeatRatio, final double molecularWeight) {
    this.specificHeatRatio = specificHeatRatio;
    this.density = density;
    this.molecularWeight = molecularWeight;
  }

  @Override
  public double getDensity() {
    return density;
  }

  public double getSpecificHeatRatio() {
    return specificHeatRatio;
  }

  public double getMolecularWeight() {
    return molecularWeight;
  }
}

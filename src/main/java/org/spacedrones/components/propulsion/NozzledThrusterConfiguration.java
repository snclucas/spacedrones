package org.spacedrones.components.propulsion;


public class NozzledThrusterConfiguration {

  private final double exitArea;
  private final double throatArea;
  private final double plenomArea;


  public NozzledThrusterConfiguration(final double exitArea, final double throatArea, final double plenomArea) {
    this.exitArea = exitArea;
    this.throatArea = throatArea;
    this.plenomArea = plenomArea;
  }

  public static NozzledThrusterConfiguration ConfigurationFromRatios(final double throatArea,
                                                              final double plenomAreaRatio,
                                                              final double exitAreaRatio) {
    return new NozzledThrusterConfiguration(throatArea * exitAreaRatio, throatArea, throatArea * plenomAreaRatio);
  }

  public double getExitArea() {
    return exitArea;
  }

  public double getThroatArea() {
    return throatArea;
  }

  public double getPlenomArea() {
    return plenomArea;
  }

  public double getExitAreaRatio() {
    return exitArea / throatArea;
  }

  public double getPlenomAreaRatio() {
    return plenomArea / throatArea;
  }
}
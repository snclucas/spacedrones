package org.spacedrones.components.computers;


import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class AuxiliaryComputer extends AbstractComputer {
  public static TypeInfo type = new TypeInfo("AuxiliaryComputer");
  public static TypeInfo category = new TypeInfo("Computer");

  AuxiliaryComputer(final String name, final BusComponentSpecification busResourceSpecification, final double maxCPUThroughput) {
    super(name, busResourceSpecification, maxCPUThroughput);
  }

  @Override
  public String describe() {
    return "AuxiliaryComputer";
  }

  @Override
  public SystemStatus runDiagnostics(final int level) {
    return null;
  }
}

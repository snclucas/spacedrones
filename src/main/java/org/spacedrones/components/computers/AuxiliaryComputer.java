package org.spacedrones.components.computers;


import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class AuxiliaryComputer extends AbstractComputer {

  AuxiliaryComputer(final String name, final BusComponentSpecification busResourceSpecification, final double maxCPUThroughput) {
    super(name, busResourceSpecification, maxCPUThroughput);
  }

  @Override
  public String description() {
    return "AuxiliaryComputer";
  }

  @Override
  public SystemStatus runDiagnostics(final int level) {
    return null;
  }
}

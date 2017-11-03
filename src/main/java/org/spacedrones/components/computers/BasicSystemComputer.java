package org.spacedrones.components.computers;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class BasicSystemComputer extends AbstractSystemComputer implements SystemComputer {
  public static TypeInfo type = new TypeInfo("BasicSystemComputer");
  public static TypeInfo category = new TypeInfo("SystemComputer");

	public BasicSystemComputer(String name, BusComponentSpecification busResourceSpecification,
														 double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something here
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}

	@Override
	public String describe() {
		return "System computer";
	}


  // ----- Taxonomy

  @Override
  public TypeInfo type() {
    return new TypeInfo(this.getClass().getSimpleName());
  }

  @Override
  public TypeInfo category() {
    return new TypeInfo("SystemComputer");
  }

}

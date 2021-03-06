package org.spacedrones.components.computers;

import org.spacedrones.components.comms.Status;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class BasicSystemComputer extends AbstractSystemComputer implements SystemComputer {

	public BasicSystemComputer(String name, BusComponentSpecification busResourceSpecification,
														 double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something here
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + name() +"] OK", Status.OK);
		return systemStatus;
	}

}

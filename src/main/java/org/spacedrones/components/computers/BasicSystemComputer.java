package org.spacedrones.components.computers;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.List;

public class BasicSystemComputer extends AbstractSystemComputer implements SystemComputer {
  public static TypeInfo type = new TypeInfo("BasicSystemComputer");
  public static TypeInfo category = new TypeInfo("SystemComputer");

	public BasicSystemComputer(String name, Bus bus, BusComponentSpecification busResourceSpecification,
														 double maxCPUThroughput) {
		super(name, bus, busResourceSpecification, maxCPUThroughput);
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

	@Override
	public List<SystemStatusMessage> checkSystems() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();

		double maximumAvailableVolume = getVolume(Unit.m3);

		SystemData data = new SystemData("maximumAvailableVolume", Double.toString(maximumAvailableVolume));
    getStorageDevice().saveData(new DataRecord("maximumAvailableVolume", data));

		systemStatusMessages.add(
				new SystemStatusMessage(this, "Available CPU throughput: " + getTotalCPUThroughputAvailable(Unit.MFLOP), getUniversalTime(), Status.INFO));
		systemStatusMessages.add(
				new SystemStatusMessage(this, "Required CPU throughput: " + getTotalCurrentCPUThroughput(Unit.MFLOP), getUniversalTime(), Status.INFO));

		if(getTotalCPUThroughputAvailable(Unit.MW) < getCurrentCPUThroughput(Unit.MW))
			systemStatusMessages.add(new SystemStatusMessage(this, "Not enough CPU", getUniversalTime(), Status.PROBLEM));

		return systemStatusMessages;
	}

	@Override
	public double getTotalCPUThroughputAvailable(Unit unit) {
		return 0;
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

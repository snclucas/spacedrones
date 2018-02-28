package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;


public abstract class AbstractDataStorageUnit extends AbstractBusComponent implements DataStore {
	
	AbstractDataStorageUnit(String name,
													BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	@Override
	public double getCurrentPower(Unit unit) {
		// Power remains constant
		return getNominalPower(unit);
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getNominalCPUThroughput(unit);
	}

	@Override
	public void tick(double dt) {
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + name() +"] OK", Status.OK);
		return systemStatus;
	}

  @Override
  public SystemStatus online() {
    SystemStatus systemStatus = new SystemStatus(this);
    systemStatus.addSystemMessage(name() + " online.", Status.OK);
    return systemStatus;
  }

}

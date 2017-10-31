package org.spacedrones.components.storage.energy;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public class Capacitor extends AbstractEnergyStorageDevice {
	public static TypeInfo type = new TypeInfo("Capacitor");
	
	public Capacitor(String name,
			BusComponentSpecification busResourceSpecification,
			double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification, storageCapacity, chargeRate,
				dischargeRate);
	}

	@Override
	public TypeInfo getType() {
		return new TypeInfo("Capacitor");
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return 0;
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return 0;
	}

	@Override
	public SystemStatus online() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String describe() {
		return "Capacitor";
	}

}

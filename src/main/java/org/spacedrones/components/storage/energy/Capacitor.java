package org.spacedrones.components.storage.energy;

import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class Capacitor extends AbstractEnergyStorageDevice {

	public Capacitor(String name,
			BusComponentSpecification busResourceSpecification,
			double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification, storageCapacity, chargeRate,
				dischargeRate);
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return 0;
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return 0;
	}

}
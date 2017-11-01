package org.spacedrones.components.storage.energy;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractEnergyStorageDevice extends AbstractBusComponent implements EnergyStorageDevice {
	
	private final double storageCapacity;
	private final double chargeRate;
	private final double dischargeRate;
	

	AbstractEnergyStorageDevice(String name,
			BusComponentSpecification busResourceSpecification, double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification);
		this.storageCapacity = storageCapacity;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
	}

	@Override
	public TypeInfo type() {
		return new TypeInfo(this.getClass().getSimpleName());
	}

	@Override
	public TypeInfo category() {
		return category;
	}

	public double getStorageCapacity() {
		return storageCapacity;
	}

	public double getChargeRate() {
		return chargeRate;
	}

	public double getDischargeRate() {
		return dischargeRate;
	}
	
	@Override
	public void tick() {
	}
	
}

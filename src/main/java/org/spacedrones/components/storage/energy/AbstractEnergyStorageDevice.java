package org.spacedrones.components.storage.energy;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractEnergyStorageDevice extends AbstractBusComponent implements EnergyStorageDevice {
	
	protected double storageCapacity;
	protected double chargeRate;
	protected double dischargeRate;
	

	public AbstractEnergyStorageDevice(String name,
			BusComponentSpecification busResourceSpecification, double storageCapacity, double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification);
		this.storageCapacity = storageCapacity;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
	}
	
	
	@Override
	public TypeInfo getCategory() {
		return category;
	}


	public double getStorageCapacity() {
		return storageCapacity;
	}


	public void setStorageCapacity(double storageCapacity) {
		this.storageCapacity = storageCapacity;
	}


	public double getChargeRate() {
		return chargeRate;
	}


	public void setChargeRate(double chargeRate) {
		this.chargeRate = chargeRate;
	}


	public double getDischargeRate() {
		return dischargeRate;
	}


	public void setDischargeRate(double dischargeRate) {
		this.dischargeRate = dischargeRate;
	}
	
	
	@Override
	public void tick() {
	}
	
}

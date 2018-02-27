package org.spacedrones.components.storage.energy;

import org.spacedrones.components.SpacecraftBusComponent;

public interface EnergyStorageDevice extends SpacecraftBusComponent {

	double getStorageCapacity();
	
	double getChargeRate();
	
	double getDischargeRate();

}

package org.spacedrones.components.storage.energy;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface EnergyStorageDevice extends SpacecraftBusComponent {
	
	TypeInfo categoryID = new TypeInfo("EnergyStorageDevice");
	
	double getStorageCapacity();
	
	double getChargeRate();
	
	double getDischargeRate();

}

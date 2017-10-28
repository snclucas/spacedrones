package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent{

	TypeInfo category = new TypeInfo("PowerGenerator");
	
	double getPowerOutput();
	double getMaximumPowerOutput();

}

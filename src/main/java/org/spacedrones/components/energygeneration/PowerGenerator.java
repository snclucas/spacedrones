package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent{
	TypeInfo category = new TypeInfo("PowerGenerator");
	TypeInfo type = category;
	
	double getPowerOutput();
	double getMaximumPowerOutput();

}

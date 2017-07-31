package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent{
	
	static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	double getPowerOutput();
	double getMaximumPowerOutput();

}

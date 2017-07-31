package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface PowerGenerator extends SpacecraftBusComponent{
	
	public static TypeInfo category() {
		return new TypeInfo("PowerGenerator");
	}
	
	public double getPowerOutput();
	public double getMaximumPowerOutput();

}

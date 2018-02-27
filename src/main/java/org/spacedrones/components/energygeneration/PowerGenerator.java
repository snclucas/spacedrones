package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;

public interface PowerGenerator extends SpacecraftBusComponent{
	double getPowerOutput();
	double getMaximumPowerOutput();

}

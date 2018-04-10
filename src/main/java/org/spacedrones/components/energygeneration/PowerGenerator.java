package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.physics.Unit;

public interface PowerGenerator extends SpacecraftBusComponent{
	double getPowerOutput(Unit unit);
}

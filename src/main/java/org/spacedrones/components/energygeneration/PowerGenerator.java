package org.spacedrones.components.energygeneration;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.UniverseAware;

public interface PowerGenerator extends SpacecraftBusComponent, UniverseAware {
	double getPowerOutput(Unit unit);
}

package org.spacedrones.spacecraft;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.Onlineable;
import org.spacedrones.components.Tickable;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.StatusProvider;


public interface Spacecraft extends StatusProvider, Onlineable, Tickable, Identifiable {

	double getMass(Unit unit);
	double getLength(Unit unit);
	double getWidth(Unit unit);
	double getHeight(Unit unit);
  double getVolume(Unit unit);

	void giveManagerHandleTo(SpacecraftManager manager);

}

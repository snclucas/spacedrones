package org.spacedrones.components.propulsion;

import org.spacedrones.components.Executable;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.physics.Unit;

public interface Engine extends SpacecraftBusComponent, Executable {
	EngineVector getEngineVector();

	boolean isVectored();
	double getPowerLevel();
	void setPowerLevel(double powerLevel);
	double getRequiredPower(double powerLevel, Unit unit);
	double getRequiredCPUThroughput(double powerLevel, Unit unit);
}

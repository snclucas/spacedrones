package org.spacedrones.components.propulsion;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

public interface Engine extends SpacecraftBusComponent {

	TypeInfo category = new TypeInfo("Engine");

	static TypeInfo type() {
		return new TypeInfo("Engine");
	}

	EngineVector getEngineVector();

	boolean isVectored();
	double getPowerLevel();
	void setPowerLevel(double powerLevel);
	double getRequiredPower(double powerLevel, Unit unit);
	double getRequiredCPUThroughput(double powerLevel, Unit unit);
}

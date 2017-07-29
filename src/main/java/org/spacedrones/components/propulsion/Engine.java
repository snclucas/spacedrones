package org.spacedrones.components.propulsion;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.Executable;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;

public interface Engine extends SpacecraftBusComponent, Executable, BusCommunicator {
	
	public static TypeInfo category() {
		return new TypeInfo("Engine");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("Engine");
	}
		
	EngineVector getEngineVector();
	
	void setPowerLevel(double powerLevel);
	
	boolean isVectored();
	double getPowerLevel();
	double getRequiredPower(double powerLevel);
    double getRequiredCPUThroughput(double powerLevel);
}

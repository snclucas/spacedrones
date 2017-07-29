package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.spacecraft.BusRequirement;


public interface ThrustingEngine extends Engine {
	
	public static TypeInfo type() {
		return new TypeInfo("ThrustingEngine");
	}
	
	BusRequirement callDrive(double powerLevel);
	BusRequirement callStop();
	BusRequirement callVector(EngineVector engineVector);

	ThrustProfile getThrustProfile();

	double[] getThrust(double[] velocity);

	double getMaximumThrust();
}

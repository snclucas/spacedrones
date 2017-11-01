package org.spacedrones.profiles;

import org.spacedrones.components.Diagnosable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.status.StatusProvider;

public interface ThrustProfile extends Diagnosable, StatusProvider {
	TypeInfo category = new TypeInfo("ThrustProfile");
	TypeInfo type = category;

	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);
	
	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();
	
}

package org.spacedrones.profiles;

import org.spacedrones.status.StatusProvider;

public interface ThrustProfile extends StatusProvider {
	double getNormalizedThrust(double powerLevel);
	double getNormalizedPower(double powerLevel);
	double getNormalizedCPU(double powerLevel);

	double[][] getNormalizedThrustProfile();
	double[][] getNormalizedPowerProfile();

}

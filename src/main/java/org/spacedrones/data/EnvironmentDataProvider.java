package org.spacedrones.data;

import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.EnvironmentData;

public interface EnvironmentDataProvider {
	EnvironmentData getEnvironmentData(Coordinates coordinates, StdAppMagnitude stdAppMagnitude);
	double getSubspaceNoise(Coordinates coordinates);
}

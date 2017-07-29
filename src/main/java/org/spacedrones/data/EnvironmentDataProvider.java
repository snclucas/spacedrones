package org.spacedrones.data;

import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.EnvironmentData;

public interface EnvironmentDataProvider {
	EnvironmentData getEnvironmentData(Coordinates coordinates);
	double getSubspaceNoise(Coordinates coordinates);
}

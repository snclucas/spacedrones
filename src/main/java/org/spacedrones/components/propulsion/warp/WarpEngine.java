package org.spacedrones.components.propulsion.warp;

import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.spacecraft.BusRequirement;

public interface WarpEngine extends Engine {
	BusRequirement callWarp(double powerLevel);
	BusRequirement callStop();
}

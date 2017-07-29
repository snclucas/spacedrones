package org.spacedrones.components.propulsion.jump;

import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.spacecraft.BusRequirement;

public interface JumpDrive extends Engine {
	BusRequirement callJump(double powerLevel);
	BusRequirement callStop();
}

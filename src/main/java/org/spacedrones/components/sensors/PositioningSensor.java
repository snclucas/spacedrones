package org.spacedrones.components.sensors;

import org.spacedrones.universe.Coordinates;

public interface PositioningSensor {
	
	Coordinates calculatePosition();

}

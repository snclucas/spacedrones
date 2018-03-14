package org.spacedrones.universe;

import org.spacedrones.components.*;
import org.spacedrones.physics.Unit;

import java.math.BigDecimal;

public interface Location extends Taxonomic {
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
	NavigationVector vectorToLocation(Location location, boolean normalized);
	BigDecimal distanceToLocation(Location location, Unit unit);
}
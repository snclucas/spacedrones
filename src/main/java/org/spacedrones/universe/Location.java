package org.spacedrones.universe;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

import java.math.BigDecimal;

public interface Location extends Identifiable {
	TypeInfo category = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
	NavigationVector vectorToLocation(Location location, boolean normalized);
	BigDecimal distanceToLocation(Location location, Unit unit);
}

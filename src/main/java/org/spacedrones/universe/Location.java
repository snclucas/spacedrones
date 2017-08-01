package org.spacedrones.universe;

import java.math.BigDecimal;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

public interface Location extends Identifiable {
	TypeInfo categoryID = new TypeInfo("Location");
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
	NavigationVector vectorToLocation(Location location, boolean normalized);
	BigDecimal distanceToLocation(Location location, Unit unit);
}

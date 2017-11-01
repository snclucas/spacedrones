package org.spacedrones.universe;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

import java.math.BigDecimal;

public interface Location extends Taxonomic {
	TypeInfo category = new TypeInfo("Location");
	TypeInfo type = category;
	
	Coordinates getCoordinates();
	BigDecimal getCoordinate(int index);
	NavigationVector vectorToLocation(Location location, boolean normalized);
	BigDecimal distanceToLocation(Location location, Unit unit);
}
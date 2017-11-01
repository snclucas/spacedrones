package org.spacedrones.universe;

import org.spacedrones.components.TypeInfo;

import java.math.BigDecimal;

public class SimpleLocation extends AbstractLocation {
	public static TypeInfo type = new TypeInfo("SimpleLocation");

	SimpleLocation(String name, Coordinates coordinates) {
		super(name, coordinates );
	}
	
	SimpleLocation(String name, Coordinates coordinates, Location relativeTo) {
		super(name, coordinates, relativeTo);
	}
	
	SimpleLocation(String name, BigDecimal ... coordComponents) {
		super(name, coordComponents);
	}

	@Override
	public String toString() {
		return getName() + ": ["+getCoordinates().toString() + "]";
	}

	public String describe() {
		return "SimpleLocation";
	}

}

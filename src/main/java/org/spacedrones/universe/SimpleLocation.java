package org.spacedrones.universe;

import java.math.BigDecimal;

public class SimpleLocation extends AbstractLocation {

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
		return name() + ": ["+getCoordinates().toString() + "]";
	}

	public String description() {
		return "SimpleLocation";
	}

}
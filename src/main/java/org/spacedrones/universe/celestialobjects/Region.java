package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.Coordinates;

public class Region extends AbstractCelestialObject {
	
	public static TypeInfo type() {
		return new TypeInfo("Region");
	}

	private double sizeOfRegion;

	public Region(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(name, coordinates, sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}

	
	@Override
	public TypeInfo getType() {
		return type();
	}


	public double getRegionSize() {
		return sizeOfRegion;
	}


	@Override
	public String describe() {
		return "An area of space defined by a location and extent.";
	}


}

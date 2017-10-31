package org.spacedrones.universe.celestialobjects;


import org.spacedrones.components.TypeInfo;

public class Region extends AbstractCelestialObject {
	public static TypeInfo type = new TypeInfo("Region");

	private double sizeOfRegion;

	public Region(SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}

	public double getRegionSize() {
		return sizeOfRegion;
	}

	@Override
	public String describe() {
		return "Region";
	}

}
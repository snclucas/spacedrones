package org.spacedrones.universe.celestialobjects;


public class Region extends AbstractCelestialObject {

	private double sizeOfRegion;

	public Region(SensorSignalResponseProfile sensorSignalResponseProfile, double sizeOfRegion) {
		super(sensorSignalResponseProfile);
		this.sizeOfRegion = sizeOfRegion;
	}


	public double getRegionSize() {
		return sizeOfRegion;
	}

}

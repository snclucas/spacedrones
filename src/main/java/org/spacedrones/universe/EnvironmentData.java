package org.spacedrones.universe;

public class EnvironmentData {
	
	private final double solarFlux;
	private final double radiationFlux;
	private final double subspaceNoise;
	
	public EnvironmentData(double solarFlux, double radiationFlux, double subspaceNoise) {
		super();
		this.solarFlux = solarFlux;
		this.radiationFlux = radiationFlux;
		this.subspaceNoise = subspaceNoise;
	}
	

	public double getSolarFlux() {
		return solarFlux;
	}
	

	public double getRadiationFlux() {
		return radiationFlux;
	}


	public double getSubspaceNoise() {
		return subspaceNoise;
	}

}

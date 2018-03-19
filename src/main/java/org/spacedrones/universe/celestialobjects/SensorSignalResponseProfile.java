package org.spacedrones.universe.celestialobjects;

public class SensorSignalResponseProfile {
	
	private final double opticalResponse;
	private final double radarResponse;
	private final double gravimetricResponse;
	private final double magnetometricResponse;
	private final double subspaceResonanceResponse;
	
	
	public SensorSignalResponseProfile(double opticalResponse,
			double radarResponse, double gravimetricResponse,
			double magnetometricResponse, double subspaceResonanceResponse) {
		this.opticalResponse = opticalResponse;
		this.radarResponse = radarResponse;
		this.gravimetricResponse = gravimetricResponse;
		this.magnetometricResponse = magnetometricResponse;
		this.subspaceResonanceResponse = subspaceResonanceResponse;
	}

	public double getOpticalResponse() {
		return opticalResponse;
	}

	public double getRadarResponse() {
		return radarResponse;
	}

	public double getGravimetricResponse() {
		return gravimetricResponse;
	}

	public double getMagnetometricResponse() {
		return magnetometricResponse;
	}

	public double getSubspaceResonanceResponse() {
		return subspaceResonanceResponse;
	}

}

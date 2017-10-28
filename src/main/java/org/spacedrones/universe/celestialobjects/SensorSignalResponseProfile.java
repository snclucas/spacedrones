package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.Physics;

import java.math.BigDecimal;

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
	
	public SignalResponse getSignalResponse(SensorType sensorType, BigDecimal atDistance) {
		if(SensorType.OPTICAL == sensorType) {
			double powerInW = (Physics.absMag2LuminosityInW(getOpticalResponse()));
			double powerInWAtDistance = Physics.opticalSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
              powerInWAtDistance,
					0.0);
		}
		else if(SensorType.RADAR == sensorType) {
			double powerInW = (Physics.absMag2LuminosityInW(getRadarResponse()));
			double powerInWAtDistance = Physics.radarSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
              powerInWAtDistance,
					0.0);
		}
		else if(SensorType.GRAVIMETRIC == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getGravimetricResponse()));
			double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
              powerInWAtDistance,
					0.0);
		}
		else if(SensorType.MAGNETOMETRIC == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getMagnetometricResponse()));
			double powerInWAtDistance = Physics.magnetometricSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
              powerInWAtDistance,
					0.0);
		}
		else if(SensorType.SUBSPACE_RESONANCE == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getSubspaceResonanceResponse()));
			double powerInWAtDistance = Physics.subspaceSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
              powerInWAtDistance,
					Physics.distanceToSubspaceSignalDispersion(atDistance).doubleValue() *
                  ((subspaceResonanceResponse>0.0)?subspaceResonanceResponse:0.0));
		}
		else return new SignalResponse(
				0.0,
				0.0);
	}

	private double getOpticalResponse() {
		return opticalResponse;
	}

	private double getRadarResponse() {
		return radarResponse;
	}

	private double getGravimetricResponse() {
		return gravimetricResponse;
	}

	private double getMagnetometricResponse() {
		return magnetometricResponse;
	}

	private double getSubspaceResonanceResponse() {
		return subspaceResonanceResponse;
	}

}

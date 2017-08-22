package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.Physics;

import java.math.BigDecimal;

public class SensorSignalResponseProfile {
	
	private double opticalResponse;
	private double radarResponse;
	private double gravimetricResponse;
	private double magnetometricResponse;
	private double subspaceResonanceResponse;
	
	
	public SensorSignalResponseProfile(double opticalResponse,
			double radarResponse, double gravimetricResponse,
			double magnetometricResponse, double subspaceResonanceResponse) {
		super();
		this.opticalResponse = opticalResponse;
		this.radarResponse = radarResponse;
		this.gravimetricResponse = gravimetricResponse;
		this.magnetometricResponse = magnetometricResponse;
		this.subspaceResonanceResponse = subspaceResonanceResponse;
	}
	
	
	public SignalResponse getSignalResponse(final TypeInfo sensorType, BigDecimal atDistance) {
		if(Sensor.OPTICAL == sensorType) {
			double powerInW = (Physics.absMag2LuminosityInW(getOpticalResponse()));
			double powerinWAtDistance = Physics.opticalSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
					powerinWAtDistance,
					0.0);
		}
		else if(Sensor.RADAR == sensorType) {
			double powerInW = (Physics.absMag2LuminosityInW(getRadarResponse()));
			double powerinWAtDistance = Physics.radarSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
					powerinWAtDistance,
					0.0);
		}
		else if(Sensor.GRAVIMETRIC == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getGravimetricResponse()));
			double powerinWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
					powerinWAtDistance,
					0.0);
		}
		else if(Sensor.MAGNETOMETRIC == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getMagnetometricResponse()));
			double powerinWAtDistance = Physics.magnetometricSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
					powerinWAtDistance,
					0.0);
		}
		else if(Sensor.SUBSPACE_RESONANCE == sensorType){
			double powerInW = (Physics.absMag2LuminosityInW(getSubspaceResonanceResponse()));
			double powerinWAtDistance = Physics.subspaceSignalAtDistance(powerInW, atDistance);
			return new SignalResponse(
					powerinWAtDistance,
					Physics.distanceToSubspaceSignalDispersion(atDistance).doubleValue() * ((subspaceResonanceResponse>0.0)?subspaceResonanceResponse:0.0));
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


  void setOpticalResponse(double opticalResponse) {
    this.opticalResponse = opticalResponse;
  }

  void setRadarResponse(double radarResponse) {
    this.radarResponse = radarResponse;
  }

  void setGravimetricResponse(double gravimetricResponse) {
    this.gravimetricResponse = gravimetricResponse;
  }

  void setMagnetometricResponse(double magnetometricResponse) {
    this.magnetometricResponse = magnetometricResponse;
  }

  void setSubspaceResonanceResponse(double subspaceResonanceResponse) {
    this.subspaceResonanceResponse = subspaceResonanceResponse;
  }
}

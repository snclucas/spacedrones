package org.spacedrones.universe.dataprovider;


import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.Physics;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

import java.math.BigDecimal;

public class LocalSignalResponseProvider implements SignalResponseProvider {

  public LocalSignalResponseProvider() {}


  @Override
  public SignalResponse getSignalResponse(final SensorSignalResponseProfile sensorSignalResponseProfile, final SensorType sensorType, final BigDecimal atDistance) {
    if(SensorType.OPTICAL == sensorType) {
      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getOpticalResponse()));
      double powerInWAtDistance = (atDistance.compareTo(BigDecimal.ZERO) != 0) ?
              Physics.opticalSignalAtDistance(powerInW, atDistance, false) : powerInW;
      double appMag = Physics.absoluteMagnitude2ApparentMagnitudeAtDistance(sensorSignalResponseProfile.getOpticalResponse(), atDistance);

      Physics.absMag2LuminosityInW(appMag);

      return new SignalResponse(powerInWAtDistance, 0.0);
    }
    else if(SensorType.RADAR == sensorType) {
      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getRadarResponse()));
      double powerInWAtDistance = Physics.radarSignalAtDistance(powerInW, atDistance);
      return new SignalResponse(powerInWAtDistance, 0.0);
    }
    else if(SensorType.GRAVIMETRIC == sensorType){
      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getGravimetricResponse()));
      double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, atDistance);
      return new SignalResponse(powerInWAtDistance, 0.0);
    }
    else if(SensorType.MAGNETOMETRIC == sensorType){
      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getMagnetometricResponse()));
      double powerInWAtDistance = Physics.magnetometricSignalAtDistance(powerInW, atDistance);
      return new SignalResponse(powerInWAtDistance, 0.0);
    }
    else if(SensorType.SUBSPACE_RESONANCE == sensorType){
      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getSubspaceResonanceResponse()));
      double powerInWAtDistance = Physics.subspaceSignalAtDistance(powerInW, atDistance);
      return new SignalResponse(
              powerInWAtDistance,
              Physics.distanceToSubspaceSignalDispersion(atDistance).doubleValue() *
                      ((sensorSignalResponseProfile.getSubspaceResonanceResponse()>0.0)?sensorSignalResponseProfile.getSubspaceResonanceResponse():0.0));
    }
    else return new SignalResponse(0.0, 0.0);
  }

  @Override
  public SignalResponse getSignalResponse(final CelestialObject object, final SensorType sensorType, final BigDecimal atDistance) {
    return getSignalResponse(object.getSensorSignalResponse(), sensorType, atDistance);
  }

}

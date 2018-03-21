package org.spacedrones.universe.dataprovider;


import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.Physics;
import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

import java.math.BigDecimal;

public class LocalSignalResponseProvider implements SignalResponseProvider {

  public LocalSignalResponseProvider() {}


  @Override
  public SignalResponse getEMSignalResponse(final SensorSignalResponseProfile sensorSignalResponseProfile, final StdAppMagnitude stdAppMagnitude, final BigDecimal atDistance) {
      double apparentMagnitude = Physics.absoluteMagnitude2ApparentMagnitudeAtDistance(sensorSignalResponseProfile.getOpticalResponse(), atDistance);
      double spectralPower = Physics.spectralFluxDensityPerMeter(apparentMagnitude, stdAppMagnitude);
//      double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getOpticalResponse()));
//      double powerInWAtDistance = (atDistance.compareTo(BigDecimal.ZERO) != 0) ?
//              Physics.opticalSignalAtDistance(powerInW, atDistance, false) : powerInW;
//      double appMag = Physics.absoluteMagnitude2ApparentMagnitudeAtDistance(sensorSignalResponseProfile.getOpticalResponse(), atDistance);
    return new SignalResponse(spectralPower, 0.0);
  }

  @Override
  public SignalResponse getEMSignalResponse(final CelestialObject object, final StdAppMagnitude stdAppMagnitudes, final BigDecimal atDistance) {
    return getEMSignalResponse(object.getSensorSignalResponse(), stdAppMagnitudes, atDistance);
  }


  @Override
  public SignalResponse getGravimetricSignalResponse(final SensorSignalResponseProfile sensorSignalResponseProfile, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }

  @Override
  public SignalResponse getGravimetricSignalResponse(final CelestialObject object, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(object.getSensorSignalResponse().getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }

  @Override
  public SignalResponse getMagnetometricSignalResponse(final SensorSignalResponseProfile sensorSignalResponseProfile, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }

  @Override
  public SignalResponse getMagnetometricSignalResponse(final CelestialObject object, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(object.getSensorSignalResponse().getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }

  @Override
  public SignalResponse getSubspaceResonanceSignalResponse(final SensorSignalResponseProfile sensorSignalResponseProfile, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(sensorSignalResponseProfile.getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }

  @Override
  public SignalResponse getSubspaceResonanceSignalResponse(final CelestialObject object, final BigDecimal distance) {
    double powerInW = (Physics.absMag2LuminosityInW(object.getSensorSignalResponse().getGravimetricResponse()));
    double powerInWAtDistance = Physics.gravimetricSignalAtDistance(powerInW, distance);
    return new SignalResponse(powerInWAtDistance, 0.0);
  }
}

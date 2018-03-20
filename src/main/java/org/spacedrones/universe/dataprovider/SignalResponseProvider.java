package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

import java.math.BigDecimal;


public interface SignalResponseProvider {
  SignalResponse getEMSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, final StdAppMagnitude stdAppMagnitude, BigDecimal distance);
  SignalResponse getEMSignalResponse(CelestialObject object, final StdAppMagnitude stdAppMagnitude, BigDecimal distance);

  SignalResponse getGravimetricSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, BigDecimal distance);
  SignalResponse getGravimetricSignalResponse(CelestialObject object, BigDecimal distance);

  SignalResponse getMagnetometricSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, BigDecimal distance);
  SignalResponse getMagnetometricSignalResponse(CelestialObject object, BigDecimal distance);

  SignalResponse getSubspaceResonanceSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, BigDecimal distance);
  SignalResponse getSubspaceResonanceSignalResponse(CelestialObject object, BigDecimal distance);

}

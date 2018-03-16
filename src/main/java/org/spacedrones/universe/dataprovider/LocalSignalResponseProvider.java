package org.spacedrones.universe.dataprovider;


import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;

public class LocalSignalResponseProvider implements SignalResponseProvider {

  public LocalSignalResponseProvider() {}

  @Override
  public SignalResponse getSignalResponse(final CelestialObject object, final SensorType sensorType, final BigDecimal distance) {
      return object.getSensorSignalResponse().getSignalResponse(sensorType, distance);
  }
}

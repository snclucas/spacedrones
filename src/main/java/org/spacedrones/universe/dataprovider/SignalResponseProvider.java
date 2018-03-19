package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;

import java.math.BigDecimal;


public interface SignalResponseProvider {
  SignalResponse getSignalResponse(SensorSignalResponseProfile sensorSignalResponseProfile, SensorType sensorType, BigDecimal distance);
  SignalResponse getSignalResponse(CelestialObject object, SensorType sensorType, BigDecimal distance);
}

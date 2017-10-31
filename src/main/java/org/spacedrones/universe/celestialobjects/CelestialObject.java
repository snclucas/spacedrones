package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;

import java.math.BigDecimal;

public interface CelestialObject {
  TypeInfo category = new TypeInfo("CelestialObject");
  TypeInfo type = category;

	String getId();
  String describe();
  TypeInfo getType();
  TypeInfo getCategory();

	SensorSignalResponseProfile getSensorSignalResponse();

	SignalResponse getSignalResponse(SensorType sensorType, BigDecimal distance);

}

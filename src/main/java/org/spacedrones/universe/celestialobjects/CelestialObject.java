package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;

import java.math.BigDecimal;

public interface CelestialObject extends Taxonomic {
  TypeInfo category = new TypeInfo("CelestialObject");
  TypeInfo type = category;

	SensorSignalResponseProfile getSensorSignalResponse();

	SignalResponse getSignalResponse(SensorType sensorType, BigDecimal distance);

}

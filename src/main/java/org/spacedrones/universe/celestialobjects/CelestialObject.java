package org.spacedrones.universe.celestialobjects;

import java.math.BigDecimal;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SignalResponse;

public interface CelestialObject {

	SensorSignalResponseProfile getSensorSignalResponse();

	SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance);

}

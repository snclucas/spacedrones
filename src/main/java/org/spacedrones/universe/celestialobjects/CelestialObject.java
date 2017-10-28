package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;

import java.math.BigDecimal;

public interface CelestialObject extends Identifiable {

	SensorSignalResponseProfile getSensorSignalResponse();

	SignalResponse getSignalResponse(SensorType sensorType, BigDecimal distance);

}

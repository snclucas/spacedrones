package org.spacedrones.universe.celestialobjects;

import org.spacedrones.components.*;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;

import java.math.BigDecimal;

public interface CelestialObject extends Taxonomic {

	SensorSignalResponseProfile getSensorSignalResponse();

	SignalResponse getSignalResponse(SensorType sensorType, BigDecimal distance);

}

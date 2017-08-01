package org.spacedrones.universe.celestialobjects;

import java.math.BigDecimal;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SignalResponse;

public interface CelestialObject extends Identifiable{
	
	static TypeInfo category() {
		return new TypeInfo("CelestialObject");
	}

	SensorSignalResponseProfile getSensorSignalResponse();
	SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance);
	//Location getLocation();
	
	
}

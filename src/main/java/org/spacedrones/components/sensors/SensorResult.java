package org.spacedrones.components.sensors;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;
import org.spacedrones.universe.NavigationVector;
import org.spacedrones.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;

public class SensorResult implements Identifiable {
	
	private final CelestialObject celestialObject;
	private final BigDecimal distance;
	private final NavigationVector navigationVector;
	private final SignalResponse signalResponse;
	private final String id;
	
	
	SensorResult(CelestialObject celestialObject, BigDecimal distance,
			NavigationVector navigationVector, SignalResponse signalResponse) {
		super();
		this.celestialObject = celestialObject;
		this.distance = distance;
		this.navigationVector = navigationVector;
		this.signalResponse = signalResponse;
		this.id = Configuration.getUUID();
	}

	CelestialObject getCelestialObject() {
		return celestialObject;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	NavigationVector getNavigationVector() {
		return navigationVector;
	}

	SignalResponse getSignalResponse() {
		return signalResponse;
	}

	@Override
	public String getName() {
		return "SensorResult" + "-" ;//+ celestialObject.getName();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String describe() {
		return "SensorResult";
	}

}

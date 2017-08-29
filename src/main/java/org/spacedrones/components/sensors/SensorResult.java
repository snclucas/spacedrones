package org.spacedrones.components.sensors;

import java.math.BigDecimal;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.universe.NavigationVector;
import org.spacedrones.universe.celestialobjects.CelestialObject;

public class SensorResult implements Identifiable {
	
	private CelestialObject celestialObject;
	private BigDecimal distance;
	private NavigationVector navigationVector;
	private SignalResponse signalResponse;
	private String ident;
	
	
	public SensorResult(CelestialObject celestialObject, BigDecimal distance,
			NavigationVector navigationVector, SignalResponse signalResponse) {
		super();
		this.celestialObject = celestialObject;
		this.distance = distance;
		this.navigationVector = navigationVector;
		this.signalResponse = signalResponse;
		this.ident = Configuration.getUUID();
	}


	public CelestialObject getCelestialObject() {
		return celestialObject;
	}


	public BigDecimal getDistance() {
		return distance;
	}


	public NavigationVector getNavigationVector() {
		return navigationVector;
	}


	public SignalResponse getSignalResponse() {
		return signalResponse;
	}


	@Override
	public String getName() {
		return "SensorResult" + "-" + celestialObject.getName();
	}


	@Override
	public String getIdent() {
		return ident;
	}


	@Override
	public String describe() {
		return "Sensor result";
	}

	
	
}

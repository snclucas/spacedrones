package org.spacedrones.universe.celestialobjects;

import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCelestialObject implements CelestialObject {

	private String opticalSignature;
	private String radarSignature;
	private String gravimetricSignature;
	private String magnetometricSignature;
	private String subspaceResonanceSignature;

	private final List<CelestialObject> celestialObjects;
	private final SensorSignalResponseProfile sensorSignalResponseProfile;

	private final String id;
	private final String name;
	
	
	public AbstractCelestialObject(String name, SensorSignalResponseProfile sensorSignalResponseProfile) {
		celestialObjects = new ArrayList<>();
		this.name = name;
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.id = Configuration.getUUID();
		generateSigniatures();
	}

	public String getId() {
		return id;
	}

  public String getName() {
    return name;
  }
	
	private void generateSigniatures() {
		opticalSignature = Configuration.getUUID();
		radarSignature = Configuration.getUUID();
		gravimetricSignature = Configuration.getUUID();
		magnetometricSignature = Configuration.getUUID();
		subspaceResonanceSignature = Configuration.getUUID();
	}

	@Override
	public SensorSignalResponseProfile getSensorSignalResponse() {
		return sensorSignalResponseProfile;
	}

	public SensorSignalResponseProfile getSensorSignalResponseProfile() {
		return sensorSignalResponseProfile;
	}

	@Override
	public SignalResponse getSignalResponse(SensorType sensorType, BigDecimal distance) {
		return sensorSignalResponseProfile.getSignalResponse(sensorType, distance);
	}

	public String getOpticalSignature() {
		return opticalSignature;
	}

	public String getRadarSignature() {
		return radarSignature;
	}

	public String getGravimetricSignature() {
		return gravimetricSignature;
	}

	public String getMagnetometricSignature() {
		return magnetometricSignature;
	}

	public String getSubspaceResonanceSignature() {
		return subspaceResonanceSignature;
	}

	public List<CelestialObject> getCelestialObjects() {
		return celestialObjects;
	}

}

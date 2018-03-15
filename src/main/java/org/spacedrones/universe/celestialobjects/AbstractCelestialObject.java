package org.spacedrones.universe.celestialobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.spacedrones.Configuration;

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
	
	public AbstractCelestialObject(SensorSignalResponseProfile sensorSignalResponseProfile) {
		celestialObjects = new ArrayList<>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.id = Configuration.getUUID();
		generateSigniatures();
	}

	public String id() {
		return id;
	}

	@JsonIgnore
  public String name() {
    return "CelestialObject";
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

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
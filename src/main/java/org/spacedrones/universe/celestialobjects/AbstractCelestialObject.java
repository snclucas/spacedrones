package org.spacedrones.universe.celestialobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SignalResponse;

public abstract class AbstractCelestialObject implements CelestialObject {

	
	private String opticalSignature;
	private String radarSignature;
	private String gravimetricSignature;
	private String magnetometricSignature;
	private String subspaceResonanceSignature;
	
	
	public static TypeInfo category() {
		return new TypeInfo("CelestialObject");
	}

	private List<CelestialObject> celestialObjects;
	private SensorSignalResponseProfile sensorSignalResponseProfile;
	
	protected final String ident;
	
	
	public AbstractCelestialObject(String name, SensorSignalResponseProfile sensorSignalResponseProfile) {
		celestialObjects = new ArrayList<>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.ident = Configuration.getUUID();
		generateSigniatures();
	}
	
	
	private void generateSigniatures() {
		opticalSignature = Configuration.getUUID();
		radarSignature = Configuration.getUUID();
		gravimetricSignature = Configuration.getUUID();
		magnetometricSignature = Configuration.getUUID();
		subspaceResonanceSignature = Configuration.getUUID();
	}


	@Override
	public String getIdent() {
		return ident;
	}


	@Override
	public final TypeInfo getCategory() {
		return category();
	}


	@Override
	public SensorSignalResponseProfile getSensorSignalResponse() {
		return sensorSignalResponseProfile;
	}


	@Override
	public String toString() {
		return getName();
	}


	public SensorSignalResponseProfile getSensorSignalResponseProfile() {
		return sensorSignalResponseProfile;
	}

	
	@Override
	public SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance) {
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

package org.spacedrones.universe.celestialobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.universe.AbstractLocation;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;
import org.spacedrones.universe.SimpleLocation;

public abstract class AbstractCelestialObject extends AbstractLocation implements CelestialObject {

	
	private String opticalSignature;
	private String radarSignature;
	private String gravimetricSignature;
	private String magnetometricSignature;
	private String subspaceResonanceSignature;
	
	
	public static TypeInfo category() {
		return new TypeInfo("CelestialObject");
	}
	
	protected Location location;
	protected List<CelestialObject> celestialObjects;
	protected SensorSignalResponseProfile sensorSignalResponseProfile;
	
	protected final String ident;
	
	
	public AbstractCelestialObject(String name, Coordinates coordinates, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates);
		celestialObjects = new ArrayList<CelestialObject>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.location = new SimpleLocation(name, coordinates);
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

	public AbstractCelestialObject(String name, Coordinates coordinates, CelestialObject relativeTo, SensorSignalResponseProfile sensorSignalResponseProfile) {
		super(name, coordinates);
		celestialObjects = new ArrayList<CelestialObject>();
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
		this.location = new SimpleLocation(name, coordinates.add(relativeTo.getLocation().getCoordinates()));
		this.ident = Configuration.getUUID();
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


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String getName() {
		return this.location.getName();
	}
	
	

	@Override
	public String toString() {
		return getName();
	}

	public SensorSignalResponseProfile getSensorSignalResponseProfile() {
		return sensorSignalResponseProfile;
	}

	public void setSensorSignalResponseProfile(
			SensorSignalResponseProfile sensorSignalResponseProfile) {
		this.sensorSignalResponseProfile = sensorSignalResponseProfile;
	}

	
	@Override
	public SignalResponse getSignalResponse(TypeInfo sensorType, BigDecimal distance) {
		SignalResponse signalResponse = sensorSignalResponseProfile.getSignalResponse(sensorType, distance);
		return signalResponse;
	}
	

	public Coordinates getCoordinates() {
		return this.location.getCoordinates();
	}
	
	public BigDecimal getCoordinate(int index) {
		return this.location.getCoordinates().get(index);
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

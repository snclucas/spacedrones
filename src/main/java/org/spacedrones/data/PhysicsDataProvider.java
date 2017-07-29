package org.spacedrones.data;

public interface PhysicsDataProvider {
	
	public static String OPTICAL_PROPAGATION_INDEX = "OPTICAL_PROPAGATION_INDEX";
	public static String RADAR_PROPAGATION_INDEX = "RADAR_PROPAGATION_INDEX";
	public static String GRAVIMETRIC_PROPAGATION_INDEX = "GRAVIMETRIC_PROPAGATION_INDEX";
	public static String MAGNETOMETRIC_PROPAGATION_INDEX = "MAGNETOMETRIC_PROPAGATION_INDEX";
	public static String SUBSPACE_PROPAGATION_INDEX = "SUBSPACE_PROPAGATION_INDEX";
	
	
	
	double getValue(String valueTag);

}

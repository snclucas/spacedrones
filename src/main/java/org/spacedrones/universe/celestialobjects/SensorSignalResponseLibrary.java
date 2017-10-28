package org.spacedrones.universe.celestialobjects;

import org.spacedrones.physics.Unit;
import org.spacedrones.universe.CelestialConstants;

public class SensorSignalResponseLibrary {
	
	public final static String SUBSPACE_BEACON = "Subspace beacon";
	public final static String BROWN_DWARF = "Brown dwarf"; 
	public final static String WHITE_DWARF = "White dwarf"; 
	
	public final static String RADIO_PULSAR = "Radio pulsar"; 
	public final static String X_RAY_PULSAR = "X-ray pulsar"; 
	public final static String GAMMA_RAY_PULSAR = "Gamma-ray pulsar"; 
	
	public final static String SUPERNOVA = "Supernova"; 
	public final static String HYPERNOVA = "Hypernova";
	public final static String BLACK_HOLE = "Black hole";
	public final static String SUPERMASSIVE_BLACK_HOLE = "Supermassive black hole";
	
	public static SensorSignalResponseProfile getStandardSignalResponseProfile(StarClass objectClassification) {
		switch(objectClassification) {
		case SUBSPACE_BEACON:
			return new SensorSignalResponseProfile(50, responseForSphere(100 * Unit.m.value(), 100.0), 0.0, 0.0, 10);
		case StarClass.O:
			return new SensorSignalResponseProfile(-5.5, responseForSphere(10 * CelestialConstants.O_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.B:
			return new SensorSignalResponseProfile(-1.74, responseForSphere(5 * CelestialConstants.B_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.A:
			return new SensorSignalResponseProfile(1.25, responseForSphere(1.7 * CelestialConstants.A_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.F:
			return new SensorSignalResponseProfile(3.0, responseForSphere(1.3 * CelestialConstants.F_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.G: //384.6e24 J/s
			return new SensorSignalResponseProfile(4.83, responseForSphere(1 * CelestialConstants.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.K:
			return new SensorSignalResponseProfile(6.5, responseForSphere(0.7 * CelestialConstants.K_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case StarClass.M:
			return new SensorSignalResponseProfile(14, responseForSphere(0.3 * CelestialConstants.M_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		
			
		case SUPERNOVA: // Brighter than 10 billion suns
			return new SensorSignalResponseProfile(-17.65, 0.0, 0.0, 100.0, 0.0);
		
			
		}
		
		return null;
	}
	
	private static double responseForSphere(double radius, double reflectanceFactor) {
		return 4 * Math.PI * Math.pow(radius, 2.0) * reflectanceFactor;
	}

}

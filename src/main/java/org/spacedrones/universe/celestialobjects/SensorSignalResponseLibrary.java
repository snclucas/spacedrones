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

  public static SensorSignalResponseProfile getStandardSignalResponseForStar(Star star) {
      return getStandardSignalResponseForStar(star.getClassification());
  }

  public static SensorSignalResponseProfile getStandardSignalResponseForStar(StarClass starClass) {
    switch (starClass) {
      case O:
        return new SensorSignalResponseProfile(-5.5, responseForSphere(10 * CelestialConstants.O_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case B:
        return new SensorSignalResponseProfile(-1.74, responseForSphere(5 * CelestialConstants.B_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case A:
        return new SensorSignalResponseProfile(1.25, responseForSphere(1.7 * CelestialConstants.A_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case F:
        return new SensorSignalResponseProfile(3.0, responseForSphere(1.3 * CelestialConstants.F_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case G: //384.6e24 J/s
        return new SensorSignalResponseProfile(4.83, responseForSphere(1 * CelestialConstants.G2V_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case K:
        return new SensorSignalResponseProfile(6.5, responseForSphere(0.7 * CelestialConstants.K_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
      case M:
        return new SensorSignalResponseProfile(14, responseForSphere(0.3 * CelestialConstants.M_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
    }
    return null;
  }

  public static SensorSignalResponseProfile getStandardSignalResponseForPlanet(Planet planet) {
    return getStandardSignalResponseForPlanet(planet.getHabitatClass());
  }

  public static SensorSignalResponseProfile getStandardSignalResponseForPlanet(PlanetClass planetClass) {
    switch (planetClass) {
      case M:
        return new SensorSignalResponseProfile(-5.5, responseForSphere(10 * CelestialConstants.O_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
    }
    return null;
  }

	public static SensorSignalResponseProfile getStandardSignalResponseProfileForObjectType(String object) {
		switch(object) {
		case SUBSPACE_BEACON:
			return new SensorSignalResponseProfile(50, responseForSphere(100 * Unit.m.value(), 100.0), 0.0, 0.0, 10);
		case SUPERNOVA: // Brighter than 10 billion suns
			return new SensorSignalResponseProfile(-17.65, 0.0, 0.0, 100.0, 0.0);
		}
		return null;
	}
	
	private static double responseForSphere(double radius, double reflectanceFactor) {
		return 4 * Math.PI * Math.pow(radius, 2.0) * reflectanceFactor;
	}

}

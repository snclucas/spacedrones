package org.spacedrones.universe.celestialobjects;

import org.spacedrones.physics.Unit;
import org.spacedrones.universe.CelestialConstants;

import java.util.HashMap;
import java.util.Map;

public class SensorSignalResponseLibrary {

  private static Map<String, StellarClassification> classifications = new HashMap<>();

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


  public static SensorSignalResponseProfile getStandardSignalResponseForStar(String starClass) {
    StellarClassification stellarClassification;

    if(classifications.containsKey(starClass)) {
      stellarClassification = classifications.get(starClass);
    }
    else {
      stellarClassification = StellarClassificationIO.getStellarClassification(starClass);
    }
    return new SensorSignalResponseProfile(
            stellarClassification.absMag,
            responseForSphere(stellarClassification.radius, 1.0),
            1000.0,
            100.0,
            0.0);
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

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    getStandardSignalResponseForStar("G2V");
    long end = System.currentTimeMillis();
    System.out.println((end - start));

    start = System.currentTimeMillis();
    getStandardSignalResponseForStar("G2V");
    end = System.currentTimeMillis();
    System.out.println((end - start));
  }

}

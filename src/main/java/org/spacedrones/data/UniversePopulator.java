package org.spacedrones.data;


import org.spacedrones.physics.*;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.*;

import java.math.*;

public class UniversePopulator {


  public static void populate(Universe universe) {
    Coordinates star1Coords = new Coordinates(
            new BigDecimal(-1000.0*Unit.Ly.value()),
            new BigDecimal(0),
            new BigDecimal(0));
    Star star1 = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star 1", star1, star1Coords, new double[]{0.0, 0.0, 0.0});

    Coordinates star2Coords = new Coordinates(
            new BigDecimal(1000.0*Unit.Ly.value()),
            new BigDecimal(1000.0*Unit.Ly.value()),
            new BigDecimal(0));
    Star star2 = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star 2", star2, star2Coords, new double[]{0.0, 0.0, 0.0});

    Coordinates star3Coords = new Coordinates(
            new BigDecimal(-1000.0*Unit.Ly.value()),
            new BigDecimal(-1000.0*Unit.Ly.value()),
            new BigDecimal(0));
    Star star3 = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star 3", star3, star3Coords, new double[]{0.0, 0.0, 0.0});

    Coordinates star4Coords = new Coordinates(
            new BigDecimal(0.0*Unit.Ly.value()),
            new BigDecimal(-1000.0*Unit.Ly.value()),
            new BigDecimal(0));
    Star star4 = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star 4", star4, star4Coords, new double[]{0.0, 0.0, 0.0});
  }


}

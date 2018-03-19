package org.spacedrones.data;


import org.spacedrones.physics.*;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.*;

import java.math.*;

public class UniversePopulator {


  public static void populate(Universe universe) {
    Coordinates starACoords = new Coordinates(
            new BigDecimal(1* Unit.AU.value()), new BigDecimal(0), new BigDecimal(0));
    Star starA = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star A", starA, starACoords, new double[]{0.0, 0.0, 0.0});


    Coordinates starBCoords = new Coordinates(
            new BigDecimal(-10*Unit.Km.value()),new BigDecimal(0), new BigDecimal(0));
    Star starB = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));
    universe.addCelestialObject("Star B", starB, starBCoords, new double[]{0.0, 0.0, 0.0});

  }


}

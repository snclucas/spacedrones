package org.spacedrones.components.sensors;


import org.junit.Before;
import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class SensorTest {

  Universe universe = Universe.getInstance();

  @Before
  public void setUp() throws Exception {

    Coordinates starACoords = new Coordinates(
            new BigDecimal(1*Unit.Km.value()), new BigDecimal(0), new BigDecimal(0));
    Star starA = new Star(StarClass.G,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));
    universe.addCelestialObject("Star A", starA, starACoords, new double[]{0.0, 0.0, 0.0});


    Coordinates starBCoords = new Coordinates(
            new BigDecimal(-10*Unit.Km.value()),new BigDecimal(0), new BigDecimal(0));
    Star starB = new Star(StarClass.G,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));
    universe.addCelestialObject("Star B", starB, starBCoords, new double[]{0.0, 0.0, 0.0});


  }


  @Test
  public void testLinearSensorArray() {
    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.class.getSimpleName(), SensorType.OPTICAL, 1);
    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, -9, 10);

    universe.addObject(sensor, new Coordinates(0.0, 0.0, 0.0), new double[]{0.0, 0.0, 0.0});

    universe.list();

    List<SensorResult> ss = sensor.passiveScan(1, sensorProfile);

    ss.forEach(
            s -> System.out.println(s.getCelestialObject().getClass().getSimpleName() + " : " + s.getDistance().round(
                    new MathContext(10, RoundingMode.HALF_UP)).toString())
    );


  }




}

package org.spacedrones.components.sensors;


import org.junit.*;
import org.spacedrones.data.*;
import org.spacedrones.physics.*;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.*;

import java.math.*;
import java.util.*;

public class StarTrackerTest {

  Universe universe = Universe.getInstance();

  @Before
  public void setUp() throws Exception {
    UniversePopulator.populate(universe);
  }


  @Test
  public void testStarTracker() {
    Sensor sensor = SensorFactory.getSensor(StarTracker.class.getSimpleName(), SensorType.OPTICAL, 1);
    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, -9, 10);

    universe.addObject(sensor, new Coordinates(0.0, 0.0, 0.0), new double[]{0.0, 0.0, 0.0});
    universe.list();

    List<SensorResult> ss = sensor.passiveScan(1, sensorProfile);

    for(SensorResult result : ss) {
      System.out.println(result.getCelestialObject().getClass().getSimpleName());
    }



  }




}

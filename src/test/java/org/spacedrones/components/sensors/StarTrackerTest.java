package org.spacedrones.components.sensors;


import org.junit.Before;
import org.junit.Test;
import org.spacedrones.data.UniversePopulator;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class StarTrackerTest {

  Universe universe = Universe.getInstance();

  @Before
  public void setUp() throws Exception {
    UniversePopulator.populate(universe);
  }


  @Test
  public void testStarTracker() {
    Sensor sensor = SensorFactory.getSensor(StarTracker.class.getSimpleName(), SensorType.OPTICAL, 1);
    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, SensorThreshold.asMagnitude(15), 10);

    universe.addObject(sensor, new Coordinates(0.0, 0.0, 0.0), new double[]{0.0, 0.0, 0.0});
    universe.list();

    List<SensorResult> ss = sensor.passiveScan(1, sensorProfile);

    for(SensorResult result : ss) {
      System.out.println(
              result.getCelestialObject().getClass().getSimpleName() + " " +
              result.getDistance().round(new MathContext(5, RoundingMode.HALF_UP)) + " " +
              result.getSignalResponse().getSignalStrength() + " " +
                      SensorThreshold.asMagnitude(12).getThresholdInWatts());
    }

// / (4*Math.PI*Math.pow(result.getDistance().doubleValue(),2))

  }




}

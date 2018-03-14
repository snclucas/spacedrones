package org.spacedrones.components.sensors;


import org.junit.Before;
import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

import java.util.List;

public class SensorTest {

  @Before
  public void setUp() throws Exception {

  }


  @Test
  public void testLinearSensorArray() {
    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.class.getSimpleName(), SensorType.OPTICAL, 1);
    //sensor.registerSystemComputer(new BasicSystemComputer("Test computer", new BusComponentSpecification(), 10* Unit.GFLOPs.value()));

    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, -9, 10);

    Universe universe = Universe.getInstance();
    universe.populate();
    universe.addObject(sensor, new Coordinates(8.0* Unit.kPc.value(), 1.0* Unit.Ly.value(), 1.0* Unit.Ly.value()), new double[]{0.0, 0.0, 0.0});

    //universe.list();

    List<SensorResult> ss = sensor.passiveScan(1, sensorProfile);

    ss.stream().forEach(s -> {
      System.out.println(s.toString());
    });


  }




}

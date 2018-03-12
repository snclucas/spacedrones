package org.spacedrones.components.sensors;


import org.junit.Before;
import org.junit.Test;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.dataprovider.LocalObjectLocationDataProvider;
import org.spacedrones.universe.dataprovider.LocalUniverseLocationDataProvider;

import java.util.List;

public class SensorTest {

  private LocalUniverseLocationDataProvider localUniverseLocationDataProvider;
  private LocalObjectLocationDataProvider objectLocationDataProvider;

  @Before
  public void setUp() throws Exception {
    localUniverseLocationDataProvider = new LocalUniverseLocationDataProvider();
    objectLocationDataProvider = new LocalObjectLocationDataProvider();
  }


  @Test
  public void testLinearSensorArray() {

    localUniverseLocationDataProvider.populate();


    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.class.getSimpleName(), SensorType.OPTICAL, 1);
    sensor.registerSystemComputer(new BasicSystemComputer("Test computer", new BusComponentSpecification(), 10* Unit.GFLOPs.value()));

    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, -9, 10);



    Universe.getInstance().populate();
    Universe.getInstance().addComponent(sensor,new Coordinates(), new double[]{0.0, 0.0, 0.0});


    List<SensorResult> ss = sensor.passiveScan(1, sensorProfile);

    //ss.stream().forEach(s -> {
    //  System.out.println(s.name());
    //});




  }




}

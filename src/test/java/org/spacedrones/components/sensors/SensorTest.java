package org.spacedrones.components.sensors;


import org.junit.Test;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

public class SensorTest {

  @Test
  public void testLinearSensorArray() {

 //   Configuration.getUniverseSpacecraftLocationDataProvider().addSpacecraft();



    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type, SensorType.OPTICAL, 1);
    sensor.registerSystemComputer(new BasicSystemComputer("Test computer", new BusComponentSpecification(), 10* Unit.GFLOPs.value()));

    SensorProfile sensorProfile = new SensorProfile(SensorType.OPTICAL, -9, 10);

    Universe.getInstance().addComponent(sensor, new Coordinates());


    sensor.passiveScan(1, sensorProfile);




  }

}

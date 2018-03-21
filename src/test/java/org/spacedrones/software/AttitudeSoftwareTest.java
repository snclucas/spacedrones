package org.spacedrones.software;


import org.junit.Before;
import org.junit.Test;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorFactory;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.StarTracker;
import org.spacedrones.data.UniversePopulator;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

public class AttitudeSoftwareTest {

  private Universe universe = Universe.getInstance();

  @Before
  public void setUp() throws Exception {
    UniversePopulator.populate(universe);
  }

  @Test
  public void testAttitudeControlSoftware() {

    Sensor sensor = SensorFactory.getSensor(StarTracker.class.getSimpleName(), SensorType.OPTICAL, 1);
    universe.addObject(sensor, new Coordinates(0.0, 0.0, 0.0), new double[]{0.0, 0.0, 0.0});

    SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());

    systemComputer.registerSpacecraftComponent(sensor);
    AttitudeSoftware attitudeSoftware = new AttitudeSoftware("");


    systemComputer.loadSoftware(attitudeSoftware);

    attitudeSoftware.init();
    attitudeSoftware.getAttitude();


  }

}

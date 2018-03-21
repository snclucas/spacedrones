package org.spacedrones.software;


import org.junit.*;
import org.spacedrones.components.computers.*;

public class AttitudeSoftwareTest {

  @Test
  public void testAttitudeControlSoftware() {

    SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());


    AttitudeSoftware attitudeSoftware = new AttitudeSoftware("");


    systemComputer.loadSoftware(attitudeSoftware);

    attitudeSoftware.init();

    attitudeSoftware.getAttitude();


  }

}

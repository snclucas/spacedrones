package org.spacedrones.universe.dataprovider;


import org.junit.*;
import org.spacedrones.components.energygeneration.*;
import org.spacedrones.components.sensors.*;
import org.spacedrones.spacecraft.*;
import org.spacedrones.universe.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ObjectLocationDataProviderTest {

  private LocalObjectLocationDataProvider objectLocationDataProvider;

  @Before
  public void setUp() throws Exception {
    objectLocationDataProvider = new LocalObjectLocationDataProvider();
  }

  @Test
  public void testAddGet() {

    Spacecraft spacecraft1 = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);

    Sensor sensor1 = SensorFactory.getSensor(SensorFactory.LinearSensorArray, SensorType.OPTICAL, 10);
    Sensor sensor2 = SensorFactory.getSensor(SensorFactory.FractalSensorArray, SensorType.OPTICAL, 10);
    PowerGenerator powerGenerator1 = PowerGenerationFactory.getPowerGenerator(SimpleSolarArray.class.getSimpleName());


    Coordinates initialCoordinates = new Coordinates(0,0,0);

    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, new double[]{0.0, 0.0, 0.0});

    objectLocationDataProvider.addComponent(sensor1, initialCoordinates, new double[]{0.0, 0.0, 0.0});
    objectLocationDataProvider.addComponent(sensor2, initialCoordinates, new double[]{0.0, 0.0, 0.0});

    objectLocationDataProvider.addComponent(powerGenerator1, initialCoordinates, new double[]{0.0, 0.0, 0.0});

    Optional<Spacecraft> sc1 = objectLocationDataProvider.getSpacecraftById(spacecraft1.id());

    Optional<Spacecraft> sc2 = objectLocationDataProvider.getObjectById(spacecraft1.id(), Spacecraft.class);

    Optional<Sensor> sen1 = objectLocationDataProvider.getObjectById(sensor1.id(), Sensor.class);
    Optional<Sensor> sen2 = objectLocationDataProvider.getObjectById(sensor1.id(), FractalSensorArray.class);
    Optional<FractalSensorArray> sen3 = objectLocationDataProvider.getObjectById(sensor1.id(), FractalSensorArray.class);

    Optional<PowerGenerator> pgen1 = objectLocationDataProvider.getObjectById(powerGenerator1.id(), PowerGenerator.class);

    Optional<PowerGenerator> pgen2 = objectLocationDataProvider.getObjectById(powerGenerator1.id(), Sensor.class);

    assertEquals("Wrong spacecraft", spacecraft1, sc1.orElse(null));


//    assertEquals("Not enough sensors", 2, objectLocationDataProvider.getAllObjectsByType(Sensor.class).size());

    //System.out.println(sen1.get().name());
//    System.out.println(sen2.get().name());
//    System.out.println(sen3.get().name());

    //System.out.println(pgen1.get().name());

    List<Spacecraft> spacecraft = objectLocationDataProvider.getAllObjectsByType(Spacecraft.class); 1

  }

}

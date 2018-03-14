package org.spacedrones.universe.dataprovider;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spacedrones.components.energygeneration.PowerGenerationFactory;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SimpleSolarArray;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.components.propulsion.EngineFactory;
import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.sensors.*;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ObjectLocationDataProviderTest {

  private LocalObjectLocationDataProvider objectLocationDataProvider;

  private Spacecraft spacecraft1 = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
  private Spacecraft spacecraft2 = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);

  private Sensor sensor1 = SensorFactory.getSensor(SensorFactory.LinearSensorArray, SensorType.OPTICAL, 10);
  private Sensor sensor2 = SensorFactory.getSensor(SensorFactory.FractalSensorArray, SensorType.OPTICAL, 10);

  private PowerGenerator powerGenerator1 = PowerGenerationFactory.getPowerGenerator(SimpleSolarArray.class.getSimpleName());
  private PowerGenerator powerGenerator2 = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.class.getSimpleName());

  private Engine engine1 = EngineFactory.getEngine(SimpleIonEngine.class.getSimpleName(), false);
  private Engine engine2 = EngineFactory.getEngine(SimpleThruster.class.getSimpleName(), false);

  private Coordinates initialCoordinates = new Coordinates(0,0,0);
  private Coordinates newCoordinates = new Coordinates(10,10,10);

  private double[] initialVelocity = new double[]{0.0, 0.0, 0.0};
  private double[] newVelocity = new double[]{10,10,10};

  @Before
  public void setUp() throws Exception {
    objectLocationDataProvider = new LocalObjectLocationDataProvider();
  }

  @After
  public void pullDown() throws Exception {
    objectLocationDataProvider = null;
  }


  @Test
  public void testAddGet() {
    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, initialVelocity);
    objectLocationDataProvider.addSpacecraft(spacecraft2, initialCoordinates, initialVelocity);

    objectLocationDataProvider.addObject(sensor1, initialCoordinates, new double[]{0.0, 0.0, 0.0});
    objectLocationDataProvider.addObject(sensor2, initialCoordinates, new double[]{0.0, 0.0, 0.0});

    objectLocationDataProvider.addObject(powerGenerator1, initialCoordinates, new double[]{0.0, 0.0, 0.0});
    objectLocationDataProvider.addObject(powerGenerator2, initialCoordinates, new double[]{0.0, 0.0, 0.0});

    objectLocationDataProvider.addObject(engine1, initialCoordinates, new double[]{0.0, 0.0, 0.0});
    objectLocationDataProvider.addObject(engine2, initialCoordinates, new double[]{0.0, 0.0, 0.0});


    List<Spacecraft> allSpacecraft = objectLocationDataProvider.getAllSpacecraft();
    assertEquals("Not enough spacecraft", 2, allSpacecraft.size());

    Optional<Spacecraft> sc1_1 = objectLocationDataProvider.getSpacecraftById(spacecraft1.id());
    Optional<Spacecraft> sc1_2 = objectLocationDataProvider.getObjectByIdAndType(spacecraft1.id(), Spacecraft.class);

    Optional<Spacecraft> sc2_1 = objectLocationDataProvider.getSpacecraftById(spacecraft2.id());
    Optional<Spacecraft> sc2_2 = objectLocationDataProvider.getObjectByIdAndType(spacecraft2.id(), Spacecraft.class);

    Optional<Spacecraft> sc_wrongid = objectLocationDataProvider.getObjectByIdAndType("wrong ID", Spacecraft.class);

    List<Spacecraft> spacecraft = objectLocationDataProvider.getAllObjectsByType(Spacecraft.class);

    assertEquals("Not enough spacecraft", 2, spacecraft.size());
    assertEquals("Wrong spacecraft", spacecraft1, sc1_1.orElse(null));
    assertEquals("Wrong spacecraft", spacecraft1, sc1_2.orElse(null));
    assertEquals("Wrong spacecraft", spacecraft2, sc2_1.orElse(null));
    assertEquals("Wrong spacecraft", spacecraft2, sc2_2.orElse(null));

    assertNull("Wrong spacecraft", sc_wrongid.orElse(null));

    List<Sensor> sensors = objectLocationDataProvider.getAllObjectsByType(Sensor.class);
    List<LinearSensorArray> linearSensorArrays = objectLocationDataProvider.getAllObjectsByType(LinearSensorArray.class);
    List<FractalSensorArray> fractalSensorArrays = objectLocationDataProvider.getAllObjectsByType(FractalSensorArray.class);
    assertEquals("Not enough sensors", 2, sensors.size());
    assertEquals("Not enough linearSensorArrays", 1, linearSensorArrays.size());
    assertEquals("Not enough fractalSensorArrays", 1, fractalSensorArrays.size());


    Optional<Sensor> sen1 = objectLocationDataProvider.getObjectByIdAndType(sensor1.id(), Sensor.class);
    Optional<Sensor> sen2 = objectLocationDataProvider.getObjectByIdAndType(sensor1.id(), LinearSensorArray.class);
    Optional<FractalSensorArray> sen3 = objectLocationDataProvider.getObjectByIdAndType(sensor1.id(), LinearSensorArray.class);

    assertEquals("Wrong sensor", sensor1, sen1.orElse(null));
    assertEquals("Wrong sensor", sensor1, sen2.orElse(null));
    assertEquals("Wrong sensor", sensor1, sen3.orElse(null));

    Optional<PowerGenerator> pgen1 = objectLocationDataProvider.getObjectByIdAndType(powerGenerator1.id(), PowerGenerator.class);
    Optional<PowerGenerator> pgen2 = objectLocationDataProvider.getObjectByIdAndType(powerGenerator1.id(), Sensor.class);




  }

  @Test
  public void testSpacecraftLocation() {
    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, initialVelocity);
    objectLocationDataProvider.addSpacecraft(spacecraft2, initialCoordinates, initialVelocity);

    Coordinates coords = objectLocationDataProvider.getSpacecraftLocation(spacecraft1.id());
    assertEquals("Wrong initial coordinates", initialCoordinates, coords);

    objectLocationDataProvider.updateSpacecraftLocation(spacecraft1.id(), newCoordinates);
    coords = objectLocationDataProvider.getSpacecraftLocation(spacecraft1.id());
    assertEquals("Wrong coordinates", newCoordinates, coords);
  }


  @Test
  public void testSpacecraftVelocity() {
    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, initialVelocity);
    objectLocationDataProvider.addSpacecraft(spacecraft2, initialCoordinates, initialVelocity);

    double[] vel = objectLocationDataProvider.getSpacecraftVelocity(spacecraft1.id());
    assertEquals("Wrong initial velocity", initialVelocity, vel);

    objectLocationDataProvider.updateSpacecraftVelocity(spacecraft1.id(), newVelocity);
    vel = objectLocationDataProvider.getSpacecraftVelocity(spacecraft1.id());
    assertEquals("Wrong velocity", newVelocity, vel);
  }

  @Test
  public void testSpacecraftDistanceBetweenSpacecraft() {
    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, initialVelocity);
    objectLocationDataProvider.addSpacecraft(spacecraft2, newCoordinates, initialVelocity);

    Coordinates coords1 = objectLocationDataProvider.getSpacecraftLocation(spacecraft1.id());
    Coordinates coords2 = objectLocationDataProvider.getSpacecraftLocation(spacecraft2.id());

    BigDecimal dist = objectLocationDataProvider.getDistanceBetweenTwoSpacecraft(spacecraft1.id(), spacecraft2.id(), Unit.m);
    BigDecimal expectedDist = BigDecimal.valueOf(Math.sqrt(Math.pow(10,2)+Math.pow(10,2)+Math.pow(10,2)));
    MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
    assertEquals("Wrong distance", 0, expectedDist.round(mc).compareTo(dist.round(mc)));
  }

  @Test
  public void testSpacecraftWithinDistance() {
    objectLocationDataProvider.addSpacecraft(spacecraft1, initialCoordinates, initialVelocity);
    objectLocationDataProvider.addSpacecraft(spacecraft2, newCoordinates, initialVelocity);

    Coordinates range = new Coordinates(5,5,5);

    List<Spacecraft> sc = objectLocationDataProvider.getSpacecraftWithinRangeOfCoordinates(initialCoordinates, new BigDecimal(5.0), Unit.m);

    assertEquals("Not enough spacecraft", 1, sc.size());

    System.out.println(sc);


  }

}

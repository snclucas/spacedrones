package org.spacedrones.universe.dataprovider;


import org.junit.*;
import org.spacedrones.spacecraft.*;
import org.spacedrones.universe.*;

public class ObjectLocationDataProviderTest {

  private ObjectLocationDataProvider objectLocationDataProvider;

  @Before
  public void setUp() throws Exception {
    objectLocationDataProvider = new LocalObjectLocationDataProvider();
  }

  @Test
  public void testAddGet() {

    Spacecraft spacecraft = SpacecraftFactory.getSpacecraft("Shuttle");

    Coordinates initialCoordinates = new Coordinates(0,0,0);

    objectLocationDataProvider.addSpacecraft(spacecraft, initialCoordinates);

    objectLocationDataProvider.getObjectByIdent(spacecraft.id());






  }

}

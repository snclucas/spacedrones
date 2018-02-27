package org.spacedrones.universe.dataprovider;


import org.junit.*;

public class LocalUniverseLocationDataProviderTest {

  private LocalUniverseLocationDataProvider localUniverseLocationDataProvider;

  @Before
  public void setUp() throws Exception {
    localUniverseLocationDataProvider = new LocalUniverseLocationDataProvider();
    localUniverseLocationDataProvider.populate();
  }

  @Test
  public void testConverters() {
  //  localUniverseLocationDataProvider.
  }

}

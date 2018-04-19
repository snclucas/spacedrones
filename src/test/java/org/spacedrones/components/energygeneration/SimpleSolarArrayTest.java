package org.spacedrones.components.energygeneration;

import org.junit.Before;
import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.data.UniversePopulator;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SimpleSolarArrayTest {

  private Universe universe = Universe.getInstance();

  @Before
  public void setUp() throws Exception {
    UniversePopulator.populate(universe);
  }
add objects and not distinguish if they are spacecraft
	/*  */
	@Test
	public void testSimpleSolarArray() {
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(SimpleSolarArray.class.getSimpleName());

		double arrayArea = 10.0* Unit.m2.value();
		double efficiency = 75 * Unit.percent.value();

		SimpleSolarArray simpleSolarArray = new SimpleSolarArray("Test simple solar aarray",
				data.getBusComponentSpecification(), arrayArea, efficiency);

    universe.addObject(simpleSolarArray, new Coordinates(0.0, 0.0, 0.0), new double[]{0.0, 0.0, 0.0});

		assertEquals("Array area of simple solar array incorrect", arrayArea, simpleSolarArray.getArrayArea(), 0.001);
		assertEquals("Efficiency of simple solar array incorrect", efficiency, simpleSolarArray.getEfficiency(), 0.001);

    simpleSolarArray.getPowerOutput(Unit.W);



	}


	@Test
	public void testPowerGeneration() {

    Optional<SimpleSolarArray> simpleSolarArray = PowerGenerationFactory.getPowerGenerator(
            SimpleSolarArray.class.getSimpleName());


    SimpleSolarArray ss = simpleSolarArray.get();

		Universe universe = Universe.getInstance();
		Spacecraft shuttle = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);

		Coordinates solCoords = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
		Star sol = new Star(StarClass.G2V,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));

		Coordinates coords = solCoords.add(
				new Coordinates(new BigDecimal(10*Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO));
		universe.addSpacecraft(shuttle, coords, new double[]{0.0, 0.0, 0.0});






	}

}

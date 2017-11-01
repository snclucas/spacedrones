package org.spacedrones.components.energygeneration;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SimpleSolarArrayTest {
	
	/*  */
	@Test
	public void testSimpleSolarArray() {
		SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(SimpleSolarArray.type);
		
		double arrayArea = 10.0* Unit.m2.value();
		double efficiency = 75 * Unit.percent.value();
		
		SimpleSolarArray simpleSolarArray = new SimpleSolarArray("Test simple solar aarray", 
				data.getBusComponentSpecification(), arrayArea, efficiency);
		assertEquals("Array area of simple solar array incorrect", arrayArea, simpleSolarArray.getArrayArea(), 0.001);
		assertEquals("Efficiency of simple solar array incorrect", efficiency, simpleSolarArray.getEfficiency(), 0.001);
		
		assertEquals("SimpleSolarArray category incorrect", SimpleSolarArray.category, simpleSolarArray.category());
		assertEquals("SimpleSolarArray type incorrect", SimpleSolarArray.type, simpleSolarArray.type());
	}
	
	
	@Test
	public void testPowerGeneration() {
		Universe universe = Universe.getInstance();
		Spacecraft shuttle = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);

		Coordinates solCoords = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
		Star sol = new Star(StarClass.G,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));

		Coordinates coords = solCoords.add(
				new Coordinates(new BigDecimal(10*Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO));
		universe.addSpacecraft(shuttle, coords);
		
		
		
		
		
		
	}

}

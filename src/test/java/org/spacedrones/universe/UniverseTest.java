package org.spacedrones.universe;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;
import org.spacedrones.utils.math.MathUtils;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UniverseTest {



	@Test
	public void testEnvronmentalData() {
		Universe universe = Universe.getInstance();

		Spacecraft spacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);

		Coordinates solCoords =  new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));


		Star sol = new Star(StarClass.G,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));
		Coordinates coords = solCoords.add(
				new Coordinates(new BigDecimal(10*Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO));
		universe.addSpacecraft(spacecraft, coords, new double[]{0.0, 0.0, 0.0});

		//Coordinates spacecraftLocation = new Coordinates(new BigDecimal(8*Unit.kPc.value() + 149600000 * Unit.Km.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
		//universe.updateSpacecraftLocation(spacecraft.getIdent(), spacecraftLocation);

		EnvironmentData data = universe.getEnvironmentData(universe.getSpacecraftLocation(spacecraft.id()));

	//	System.out.println(SpacecraftFirmware.getTotalPowerAvailable(spacecraft.getSpacecraftBus()));

	}


	@Test
	public void testCelestialObjects() {
		//Setup the universe
		Universe universe = Universe.getInstance();

		BigDecimal coord1 = new BigDecimal(10000000000000000.0); // 8kPc from galactic center
		BigDecimal coord2 = new BigDecimal(10000000000000000.0); // 2.3 Ly
		BigDecimal coord3 = new BigDecimal(10000000000000000.0); // 100 Ly above galactic plane

		Star sol = new Star(StarClass.G,
				new SensorSignalResponseProfile(1.0, 1.0, 1.0, 1.0, 1.0));

		Coordinates solCoords = new Coordinates(coord1, coord2, coord3);

		universe.addCelestialObject("Sol", sol, solCoords, new double[]{0.0, 0.0, 0.0});

		assertEquals("Sol coord1 incorrectly set", coord1.doubleValue(), solCoords.get(0).doubleValue(), 0.001);
		assertEquals("Sol coord2 incorrectly set", coord2.doubleValue(), solCoords.get(1).doubleValue(), 0.001);
		assertEquals("Sol coord3 incorrectly set", coord3.doubleValue(), solCoords.get(2).doubleValue(), 0.001);


		//Now add a companion star, Nemesis with offset coordinates

		BigDecimal coordOffset1 = new BigDecimal(20000000000000000000.0); // 8kPc from galactic center
		BigDecimal coordOffset2 = new BigDecimal(20000000000000000000.0); // 2.3 Ly
		BigDecimal coordOffset3 = new BigDecimal(20000000000000000000.0); // 100 Ly above galactic plane

		Coordinates nemesisCoords = new Coordinates(coordOffset1, coordOffset2, coordOffset3);
		nemesisCoords = nemesisCoords.add(solCoords);

		Star nemesis = new Star(StarClass.G,
				new SensorSignalResponseProfile(1.0, 1.0, 1.0, 1.0, 1.0));

		assertEquals("Nemesis coord1 incorrectly set", coord1.doubleValue()+coordOffset1.doubleValue(), nemesisCoords.get(0).doubleValue(), 0.001);
		assertEquals("Nemesis coord2 incorrectly set", coord2.doubleValue()+coordOffset2.doubleValue(), nemesisCoords.get(1).doubleValue(), 0.001);
		assertEquals("Nemesis coord3 incorrectly set", coord3.doubleValue()+coordOffset3.doubleValue(), nemesisCoords.get(2).doubleValue(), 0.001);


		BigDecimal dx = coord1.subtract(coordOffset1);
		BigDecimal dy = coord2.subtract(coordOffset2);
		BigDecimal dz = coord3.subtract(coordOffset3);



		BigDecimal expectedDistanceFromSolToNemesis =
				MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));

		//BigDecimal distanceFromSolToNemesis = sol.distanceToLocation(nemesis);

		//	System.out.println(Math.sqrt(coord1*coord1) + " " + coord2 + " " + coord3);
		//System.out.println(Math.sqrt(coordOffset1*coordOffset1) + " " + coordOffset2 + " " + coordOffset3);

		//System.out.println(sol.getCoordinates().getCoordinate(0) + " " + sol.getCoordinates().getCoordinate(1) + " " + sol.getCoordinates().getCoordinate(2));
		//	System.out.println(coordOffset1 + " " + coordOffset2 + " " + coordOffset3);

		//System.out.println(expectedDistanceFromSolToNemesis.doubleValue()/distanceFromSolToNemesis.doubleValue());

		//assertEquals("Distance from Sol to Nemesis incorrect", expectedDistanceFromSolToNemesis.doubleValue(), distanceFromSolToNemesis.doubleValue(), 0.001);

		//Star alphaCenturi = new Star(3,"Alpha centuri",
		//		new Coordinates(8*Unit.kPc + 2.98*Unit.Ly,2.83* Unit.Ly,101.34*Unit.Ly));
		//universe.addLocation(alphaCenturi);



		//Setup subspace beacons

		//Above Sol north pole, 1e8 Km
		//universe.addLocation(new SubspaceBeacon(3,"SolBeacon", new Coordinates(0.0,0.0,1e8*Unit.Km), sol));




		//System.out.println(sol.distanceToLocation(alphaCenturi) / Unit.Ly);
		//System.out.println(sol.vectorToLocation(alphaCenturi, true));

	}




}

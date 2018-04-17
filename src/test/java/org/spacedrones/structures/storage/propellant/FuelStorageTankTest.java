package org.spacedrones.structures.storage.propellant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.Configuration;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.consumables.FuelConstituent;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.NoFuelInTankException;
import org.spacedrones.physics.Unit;

import static org.junit.Assert.assertEquals;

public class FuelStorageTankTest {

	SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
	double capacity = 1000 * Unit.l.value();



	@Test
	public void testLiquidTank() {



		Tank testLiquidTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.class.getSimpleName(), capacity);

		Tank testCryoTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.class.getSimpleName(), capacity);

		assertEquals("Tank capacity should be 1000L", capacity, testCryoTank.getCapacity(), 0.001);
		assertEquals("Fuel tank should be empty on creation", 0.0, testCryoTank.getAmountOfFuelInTank(), 0.001);

		FuelConstituent fuel = spacecraftDataProvider.getLiquid(Fuel.LIQUID_XENON);

		//Add 500L of fuel
		testCryoTank.setContent(fuel, 436.45 * Unit.l.value());
		assertEquals("Fuel tank should have 436.45L", 436.45 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 43.645 * Unit.percent.value(), testCryoTank.getLevel(), 0.001);

		//Fill 100L of fuel
		testCryoTank.fill(100.00 * Unit.l.value());
		assertEquals("Fuel tank should have 536.45L", 536.45 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 53.645 * Unit.percent.value(), testCryoTank.getLevel(), 0.001);

		//Try to overfill the tank -
		testCryoTank.setContent(fuel, 1000 * Unit.l.value());
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);

		testCryoTank.fill(10000 * Unit.l.value());
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.empty(300 * Unit.l.value());
		assertEquals("Fuel tank should have 700L", 700 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.empty(800 * Unit.l.value());
		assertEquals("Fuel tank should have 0L", 0 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);


		assertEquals("Nominal and operating power for fuel tank not equal", testCryoTank.getNominalPower(Unit.MW), testCryoTank.getCurrentPower(Unit.MW), 0.0001);
		assertEquals("Nominal and operating CPU for fuel tank not equal", testCryoTank.getNominalCPUThroughput(Unit.MFLOPs), testCryoTank.getCurrentCPUThroughput(Unit.MFLOPs), 0.0001);
	}


	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=NoFuelInTankException.class)
	public void testTankThrowsErrorIfNoFuel() {
		// Try to get the fuel, should throw NoFuelInTankException
		Tank testTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.class.getSimpleName(), capacity);
		testTank.getContent();
	}




}
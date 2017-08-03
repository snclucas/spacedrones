package org.spacedrones.structures.storage.fuel;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.Configuration;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.NoFuelInTankException;
import org.spacedrones.physics.Unit;

public class FuelStorageTankTest {

	SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
	double capacity = 1000 * Unit.l.value();



	@Test
	public void testLiquidTank() {
		
		
		
		FuelStorageTank testLiquidTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.type(), capacity);
		assertEquals("LiquidStorageTank category ID incorrect", FuelStorageTank.categoryID, testLiquidTank.getCategory());
		assertEquals("LiquidStorageTank type ID incorrect", LiquidStorageTank.type(), testLiquidTank.getType());
		
		
		FuelStorageTank testCryoTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type(), capacity);
		
		assertEquals("CryogenicLiquidStorageTank category ID incorrect", FuelStorageTank.categoryID, testCryoTank.getCategory());
		assertEquals("CryogenicLiquidStorageTank type ID incorrect", CryogenicLiquidStorageTank.type(), testCryoTank.getType());

		assertEquals("Tank capacity should be 1000L", capacity, testCryoTank.getCapacity(), 0.001);
		assertEquals("Fuel tank should be empty on creation", 0.0, testCryoTank.getAmountOfFuelInTank(), 0.001);

		Fuel fuel = spacecraftDataProvider.getFuel(Fuel.LIQUID_XENON);

		//Add 500L of fuel
		testCryoTank.setFuel(fuel, 436.45 * Unit.l.value());
		assertEquals("Fuel tank should have 436.45L", 436.45 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 43.645 * Unit.percent.value(), testCryoTank.getFuelLevel(), 0.001);

		//Fill 100L of fuel
		testCryoTank.fillFuel(100.00 * Unit.l.value());
		assertEquals("Fuel tank should have 536.45L", 536.45 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		assertEquals("Fuel tank should have 436.45L", 53.645 * Unit.percent.value(), testCryoTank.getFuelLevel(), 0.001);

		//Try to overfill the tank - 
		testCryoTank.setFuel(fuel, 1000 * Unit.l.value());	
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		
		testCryoTank.fillFuel(10000 * Unit.l.value());	
		assertEquals("Fuel tank should have 1000L", 1000 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.removeFuel(300 * Unit.l.value());
		assertEquals("Fuel tank should have 700L", 700 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);


		testCryoTank.removeFuel(800 * Unit.l.value());
		assertEquals("Fuel tank should have 0L", 0 * Unit.l.value(), testCryoTank.getAmountOfFuelInTank(), 0.001);
		
		
		assertEquals("Nominal and operating power for fuel tank not equal", testCryoTank.getNominalPower(Unit.MW), testCryoTank.getCurrentPower(Unit.MW), 0.0001);
		assertEquals("Nominal and operating CPU for fuel tank not equal", testCryoTank.getNominalCPUThroughput(Unit.MFLOP), testCryoTank.getCurrentCPUThroughput(Unit.MFLOP), 0.0001);
	}


	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=NoFuelInTankException.class)
	public void testTankThrowsErrorIfNoFuel() {
		// Try to get the fuel, should throw NoFuelInTankException
		FuelStorageTank testTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type(), capacity);
		testTank.getFuel();
	}




}
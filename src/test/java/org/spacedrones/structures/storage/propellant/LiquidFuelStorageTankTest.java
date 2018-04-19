package org.spacedrones.structures.storage.propellant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.EmptyTankException;
import org.spacedrones.materials.Liquid;
import org.spacedrones.physics.Unit;

import static org.junit.Assert.assertEquals;

public class LiquidFuelStorageTankTest {

	SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
	double capacity = 1000 * Unit.l.value();



	@Test
	public void testLiquidTank() {
		Liquid fuel = spacecraftDataProvider.getLiquid(Liquid.LIQUID_XENON);

		Tank testLiquidTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.class.getSimpleName(), capacity);

		assertEquals("Tank capacity should be 1000L", capacity, testLiquidTank.getCapacity(), 0.001);
		assertEquals("LiquidFuel tank should be empty on creation", 0.0, testLiquidTank.getVolumeLevel(), 0.001);



		//Add 500L of fluid
    testLiquidTank.setContents(fuel);
    testLiquidTank.fill(436.45 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 436.45L", 436.45 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);
		assertEquals("LiquidFuel tank should have 436.45L", 43.645 * Unit.percent.value(), testLiquidTank.getLevel(), 0.001);

		//Fill 100L of fluid
    testLiquidTank.fill(100.00 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 536.45L", 536.45 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);
		assertEquals("LiquidFuel tank should have 436.45L", 53.645 * Unit.percent.value(), testLiquidTank.getLevel(), 0.001);

		//Try to overfill the tank -
    testLiquidTank.fill(1000 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 1000L", 1000 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);

    testLiquidTank.fill(10000 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 1000L", 1000 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);


    testLiquidTank.empty(300 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 700L", 700 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);


    testLiquidTank.empty(800 * Unit.l.value());
		assertEquals("LiquidFuel tank should have 0L", 0 * Unit.l.value(), testLiquidTank.getVolumeLevel(), 0.001);


		assertEquals("Nominal and operating power for fluid tank not equal", testLiquidTank.getNominalPower(Unit.MW), testLiquidTank.getCurrentPower(Unit.MW), 0.0001);
		assertEquals("Nominal and operating CPU for fluid tank not equal", testLiquidTank.getNominalCPUThroughput(Unit.MFLOPs), testLiquidTank.getCurrentCPUThroughput(Unit.MFLOPs), 0.0001);
	}


	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=EmptyTankException.class)
	public void testTankThrowsErrorIfNoFuel() {
		// Try to get the fluid, should throw EmptyTankException
		Tank testTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.class.getSimpleName(), capacity);
		testTank.getContents();
	}




}
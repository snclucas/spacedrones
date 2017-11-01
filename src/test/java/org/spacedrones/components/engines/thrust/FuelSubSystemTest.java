package org.spacedrones.components.engines.thrust;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.propulsion.thrust.FuelSubSystem;
import org.spacedrones.components.propulsion.thrust.FuelSubSystemFactory;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.spacedrones.structures.storage.fuel.FuelStorageTank;
import org.spacedrones.structures.storage.fuel.FuelStorageTankFactory;

import static org.junit.Assert.assertEquals;


public class FuelSubSystemTest {

	private SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

	/*  */
	@Test
	public void testFuelSubSystem() {
		double tankCapacity = 100 * Unit.l.value();
		FuelStorageTank hydrazineTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type, tankCapacity);

		assertEquals("Expected capacity of tank not correct", tankCapacity, hydrazineTank.getCapacity(), 0.001);

		//Should have no fuel
		assertEquals("Fuel tank should be empty.", 0.0, hydrazineTank.getAmountOfFuelInTank(), 0.001);

		double volumeOfFuelTankAlone = hydrazineTank.getVolume(Unit.m3);
		double massOfFuelTankAlone = hydrazineTank.getMass(Unit.kg);

		Fuel hydrazineFuel = spacecraftDataProvider.getFuel(Fuel.HYDRAZINE);
		hydrazineTank.setFuel(hydrazineFuel, tankCapacity);

		//Should have 100L of fuel
		assertEquals("Expected fuel level of tank not correct", tankCapacity, hydrazineTank.getAmountOfFuelInTank(), 0.001);

		double massOfFuelTankPlusHydrazineFuel = hydrazineTank.getMass(Unit.kg);
		double expectedMassOfFuelTankPlusHydrazineFuel = massOfFuelTankAlone + (hydrazineFuel.getDensity() * tankCapacity);

		double volumeOfFuelTankPlusHydrazineFuel = hydrazineTank.getVolume(Unit.m3);
		double expectedVolumeOfFuelTankPlusHydrazineFuel = tankCapacity * 1.3; // Cryogenic tanks have a volume scale of 1.3 * capacity of tank.

		assertEquals("Expected mass of fuel tank + fuel not correct", massOfFuelTankPlusHydrazineFuel, expectedMassOfFuelTankPlusHydrazineFuel, 0.001);
		assertEquals("Expected volume of fuel tank + fuel not correct", volumeOfFuelTankPlusHydrazineFuel, expectedVolumeOfFuelTankPlusHydrazineFuel, 0.001);

		FuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(FuelSubSystem.BASIC_FUEL_SUBSYSTEM, FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);

		double expectedMassOfFuelSubSystemOnly = 250 * Unit.kg.value(); // Basic fuel sub system
		double massOfFuelSubSystemOnly = fuelDeliverySystem.getMass(Unit.kg);
		assertEquals("Expected mass of fuel subsystem only not correct", expectedMassOfFuelSubSystemOnly, massOfFuelSubSystemOnly, 0.001);


		double expectedVolumeOfFuelSubSystemOnly = 10.0 * Unit.m3.value();
		double volumeOfFuelSubSystemOnly = fuelDeliverySystem.getVolume(Unit.m3);
		assertEquals("Expected volume of fuel subsystem not correct", expectedVolumeOfFuelSubSystemOnly, volumeOfFuelSubSystemOnly, 0.001);


		fuelDeliverySystem.addFuelTank(hydrazineTank);


		double expectedTotalMassOfFuelSubsystem = expectedMassOfFuelSubSystemOnly + expectedMassOfFuelTankPlusHydrazineFuel;
		double expectedTotalVolumeOfFuelSubsystem = expectedVolumeOfFuelSubSystemOnly + volumeOfFuelTankAlone;

		assertEquals("Expected total mass of fuel subsystem not correct", expectedTotalMassOfFuelSubsystem, fuelDeliverySystem.getMass(Unit.kg), 0.001);

		assertEquals("Expected total volume of fuel subsystem not correct", expectedTotalVolumeOfFuelSubsystem, fuelDeliverySystem.getVolume(Unit.m3), 0.001);


		// Add a liquid Xenon tank
		
		FuelStorageTank xenonTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type, tankCapacity);
		Fuel liquidXenon = spacecraftDataProvider.getFuel(Fuel.LIQUID_XENON);
		// Fill it up with liquid Xenon
		xenonTank.setFuel(liquidXenon, tankCapacity);

		fuelDeliverySystem.addFuelTank(xenonTank);
		
		double expectedMassOfFuelSubSystemWithHydrazineAndXenonTank = expectedTotalMassOfFuelSubsystem + massOfFuelTankAlone + (liquidXenon.getDensity() * tankCapacity);
		double expectedVolumeOfFuelSubSystemWithHydrazineAndXenonTank = expectedTotalVolumeOfFuelSubsystem + xenonTank.getVolume(Unit.m3);
		

		assertEquals("Fuel subsystem mass (Hydrazine + Xenon) incorrect.", expectedMassOfFuelSubSystemWithHydrazineAndXenonTank, fuelDeliverySystem.getMass(Unit.kg), 0.001);
		assertEquals("Fuel subsystem volume (Hydrazine + Xenon) incorrect.", expectedVolumeOfFuelSubSystemWithHydrazineAndXenonTank, fuelDeliverySystem.getVolume(Unit.m3), 0.001);

	}


}

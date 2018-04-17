package org.spacedrones.components.engines.thrust;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.propulsion.thrust.SimpleFuelSubSystem;
import org.spacedrones.components.propulsion.thrust.FuelSubSystemFactory;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.consumables.FuelConstituent;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.storage.propellant.CryogenicLiquidStorageTank;
import org.spacedrones.structures.storage.propellant.Tank;
import org.spacedrones.structures.storage.propellant.FuelStorageTankFactory;

import static org.junit.Assert.assertEquals;


public class FuelSubSystemTest {

	private SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

	/*  */
	@Test
	public void testFuelSubSystem() {
		double tankCapacity = 100 * Unit.l.value();
		Tank hydrazineTank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.class.getSimpleName(), tankCapacity);

		assertEquals("Expected capacity of tank not correct", tankCapacity, hydrazineTank.getCapacity(), 0.001);

		//Should have no fuel
		assertEquals("Fuel tank should be empty.", 0.0, hydrazineTank.getAmountOfFuelInTank(), 0.001);

		double volumeOfFuelTankAlone = hydrazineTank.getVolume(Unit.m3);
		double massOfFuelTankAlone = hydrazineTank.getMass(Unit.kg);


		FuelConstituent hydrazineFuel = spacecraftDataProvider.getLiquid(Fuel.HYDRAZINE);

		hydrazineTank.setContent(hydrazineFuel, tankCapacity);

		//Should have 100L of fuel
		assertEquals("Expected fuel level of tank not correct", tankCapacity, hydrazineTank.getAmountOfFuelInTank(), 0.001);

		double massOfFuelTankPlusHydrazineFuel = hydrazineTank.getMass(Unit.kg);
		double expectedMassOfFuelTankPlusHydrazineFuel = massOfFuelTankAlone + (hydrazineFuel.getDensity() * tankCapacity);

		double volumeOfFuelTankPlusHydrazineFuel = hydrazineTank.getVolume(Unit.m3);
		double expectedVolumeOfFuelTankPlusHydrazineFuel = tankCapacity * 1.3; // Cryogenic tanks have a volume scale of 1.3 * capacity of tank.

		assertEquals("Expected mass of fuel tank + fuel not correct", massOfFuelTankPlusHydrazineFuel, expectedMassOfFuelTankPlusHydrazineFuel, 0.001);
		assertEquals("Expected volume of fuel tank + fuel not correct", volumeOfFuelTankPlusHydrazineFuel, expectedVolumeOfFuelTankPlusHydrazineFuel, 0.001);

		SimpleFuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(SimpleFuelSubSystem.BASIC_FUEL_SUBSYSTEM, SimpleFuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);

		double expectedMassOfFuelSubSystemOnly = 250 * Unit.kg.value(); // Basic fuel sub system
		double massOfFuelSubSystemOnly = fuelDeliverySystem.getMass(Unit.kg);
		assertEquals("Expected mass of fuel subsystem only not correct", expectedMassOfFuelSubSystemOnly, massOfFuelSubSystemOnly, 0.001);


		double expectedVolumeOfFuelSubSystemOnly = 10.0 * Unit.m3.value();
		double volumeOfFuelSubSystemOnly = fuelDeliverySystem.getVolume(Unit.m3);
		assertEquals("Expected volume of fuel subsystem not correct", expectedVolumeOfFuelSubSystemOnly, volumeOfFuelSubSystemOnly, 0.001);


		fuelDeliverySystem.setFuelTank(hydrazineTank);


		double expectedTotalMassOfFuelSubsystem = expectedMassOfFuelSubSystemOnly + expectedMassOfFuelTankPlusHydrazineFuel;
		double expectedTotalVolumeOfFuelSubsystem = expectedVolumeOfFuelSubSystemOnly + volumeOfFuelTankAlone;

		assertEquals("Expected total mass of fuel subsystem not correct", expectedTotalMassOfFuelSubsystem, fuelDeliverySystem.getMass(Unit.kg), 0.001);

		assertEquals("Expected total volume of fuel subsystem not correct", expectedTotalVolumeOfFuelSubsystem, fuelDeliverySystem.getVolume(Unit.m3), 0.001);


    System.out.println(hydrazineTank.getAmountOfFuelInTank());
    fuelDeliverySystem.setFuelFlowRate(1.0, Unit.ls);

    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getAmountOfFuelInTank() / Unit.l.value());
    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getAmountOfFuelInTank() / Unit.l.value());
    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getAmountOfFuelInTank() / Unit.l.value());

    fuelDeliverySystem.setFuelFlowRate(10.0, Unit.ls);

    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getAmountOfFuelInTank() / Unit.l.value());
	}


}

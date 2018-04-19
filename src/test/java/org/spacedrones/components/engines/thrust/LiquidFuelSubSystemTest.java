package org.spacedrones.components.engines.thrust;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.propulsion.thrust.FuelSubSystemFactory;
import org.spacedrones.components.propulsion.thrust.SimpleFuelSubSystem;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.materials.Liquid;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.storage.propellant.FuelStorageTankFactory;
import org.spacedrones.structures.storage.propellant.LiquidStorageTank;
import org.spacedrones.structures.storage.propellant.Tank;

import static org.junit.Assert.assertEquals;


public class LiquidFuelSubSystemTest {

	private SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

	/*  */
	@Test
	public void testFuelSubSystem() {
		double tankCapacity = 100 * Unit.l.value();
		Tank hydrazineTank = FuelStorageTankFactory.getFuelStorageTank(LiquidStorageTank.class.getSimpleName(), tankCapacity);

		assertEquals("Expected capacity of tank not correct", tankCapacity, hydrazineTank.getCapacity(), 0.001);

		//Should have no fluid
		assertEquals("LiquidFuel tank should be empty.", 0.0, hydrazineTank.getVolumeLevel(), 0.001);

		double volumeOfFuelTankAlone = hydrazineTank.getVolume(Unit.m3);
		double massOfFuelTankAlone = hydrazineTank.getMass(Unit.kg);


		Liquid hydrazineFuel = spacecraftDataProvider.getLiquid(Liquid.HYDRAZINE);

		hydrazineTank.setContents(hydrazineFuel);

		//Should have 100L of fluid
		assertEquals("Expected fluid level of tank not correct", tankCapacity, hydrazineTank.getVolumeLevel(), 0.001);

		double massOfFuelTankPlusHydrazineFuel = hydrazineTank.getMass(Unit.kg);
		double expectedMassOfFuelTankPlusHydrazineFuel = massOfFuelTankAlone + (hydrazineFuel.getDensity() * tankCapacity);

		double volumeOfFuelTankPlusHydrazineFuel = hydrazineTank.getVolume(Unit.m3);
		double expectedVolumeOfFuelTankPlusHydrazineFuel = tankCapacity * 1.3; // Cryogenic tanks have a volume scale of 1.3 * capacity of tank.

		assertEquals("Expected mass of fluid tank + fluid not correct", massOfFuelTankPlusHydrazineFuel, expectedMassOfFuelTankPlusHydrazineFuel, 0.001);
		assertEquals("Expected volume of fluid tank + fluid not correct", volumeOfFuelTankPlusHydrazineFuel, expectedVolumeOfFuelTankPlusHydrazineFuel, 0.001);

		SimpleFuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(SimpleFuelSubSystem.BASIC_FUEL_SUBSYSTEM, SimpleFuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);

		double expectedMassOfFuelSubSystemOnly = 250 * Unit.kg.value(); // Basic fluid sub system
		double massOfFuelSubSystemOnly = fuelDeliverySystem.getMass(Unit.kg);
		assertEquals("Expected mass of fluid subsystem only not correct", expectedMassOfFuelSubSystemOnly, massOfFuelSubSystemOnly, 0.001);


		double expectedVolumeOfFuelSubSystemOnly = 10.0 * Unit.m3.value();
		double volumeOfFuelSubSystemOnly = fuelDeliverySystem.getVolume(Unit.m3);
		assertEquals("Expected volume of fluid subsystem not correct", expectedVolumeOfFuelSubSystemOnly, volumeOfFuelSubSystemOnly, 0.001);


		fuelDeliverySystem.setFuelTank(hydrazineTank);


		double expectedTotalMassOfFuelSubsystem = expectedMassOfFuelSubSystemOnly + expectedMassOfFuelTankPlusHydrazineFuel;
		double expectedTotalVolumeOfFuelSubsystem = expectedVolumeOfFuelSubSystemOnly + volumeOfFuelTankAlone;

		assertEquals("Expected total mass of fluid subsystem not correct", expectedTotalMassOfFuelSubsystem, fuelDeliverySystem.getMass(Unit.kg), 0.001);

		assertEquals("Expected total volume of fluid subsystem not correct", expectedTotalVolumeOfFuelSubsystem, fuelDeliverySystem.getVolume(Unit.m3), 0.001);


    System.out.println(hydrazineTank.getVolumeLevel());
    fuelDeliverySystem.setFuelFlowRate(1.0, Unit.ls);

    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getVolumeLevel() / Unit.l.value());
    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getVolumeLevel() / Unit.l.value());
    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getVolumeLevel() / Unit.l.value());

    fuelDeliverySystem.setFuelFlowRate(10.0, Unit.ls);

    fuelDeliverySystem.tick(1.0);
    System.out.println(hydrazineTank.getVolumeLevel() / Unit.l.value());
	}


}

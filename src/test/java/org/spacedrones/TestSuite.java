package org.spacedrones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.spacedrones.components.ComponentsTestSuite;
import org.spacedrones.components.computers.ComputerTestSuite;
import org.spacedrones.components.energygeneration.EnergyGenerationTestSuite;
import org.spacedrones.components.energygeneration.SubspacePowerGeneratorTest;
import org.spacedrones.components.engines.thrust.FuelSubSystemTest;
import org.spacedrones.components.propulsion.PropulsionTestSuite;
import org.spacedrones.components.sensors.SensorTestSuite;
import org.spacedrones.consumables.FuelTest;
import org.spacedrones.data.SpacecraftComponentDataTest;
import org.spacedrones.materials.CompositeMaterialTest;
import org.spacedrones.physics.PhysicsTest;
import org.spacedrones.physics.UnitsTest;
import org.spacedrones.software.PropulsionManagementSoftwareTest;
import org.spacedrones.spacecraft.SpacecraftTestSuite;
import org.spacedrones.status.SystemStatusTest;
import org.spacedrones.structures.hulls.HullTest;
import org.spacedrones.structures.storage.fuel.FuelStorageTankTest;
import org.spacedrones.universe.UniverseTestSuite;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfileTest;
import org.spacedrones.universe.celestialobjects.SubspaceBeaconTest;
import org.spacedrones.utils.math.DistanceSolverTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EnergyGenerationTestSuite.class,
	SubspaceBeaconTest.class,
	SensorTestSuite.class,
	SubspacePowerGeneratorTest.class,
	SpacecraftTestSuite.class,
	FuelSubSystemTest.class,
	FuelStorageTankTest.class,
	PropulsionTestSuite.class,
	ComputerTestSuite.class,
	UniverseTestSuite.class,
	UnitsTest.class,
	PhysicsTest.class,
	ComponentsTestSuite.class,
	HullTest.class,
	FuelTest.class,
	DistanceSolverTest.class,
	CompositeMaterialTest.class,
	SpacecraftComponentDataTest.class,
	PropulsionManagementSoftwareTest.class,
	SensorSignalResponseProfileTest.class,
	SystemStatusTest.class
})

public class TestSuite {


}


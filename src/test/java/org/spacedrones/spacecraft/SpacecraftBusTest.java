package org.spacedrones.spacecraft;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.sensors.*;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class SpacecraftBusTest {

	private Hull hull = HullFactory.getHull("Shuttle");
	private Bus spacecraftBus = null;
	Spacecraft spacecraft = null;

	@Before
	public void setUp() {
		SystemComputer computer = ComputerFactory.getSystemComputer(BasicSystemComputer.type);
		spacecraftBus = new SpacecraftBus(computer);
	}

	@Test
	public void testSpacecraftBus() {
		//assertEquals("Category for bus incorrect", Bus.category, spacecraftBus.category());

		assertEquals("There should be no bus components", 0, spacecraftBus.getComponents().size());
		assertNull(spacecraftBus.getSystemComputer());

		assertNotNull(spacecraftBus.getSystemComputer());

		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.type, SensorType.OPTICAL, 1);
		spacecraftBus.register(fractalSensor);


		assertEquals("There should be 2 bus components", 2, spacecraftBus.getComponents().size());

		assertEquals("There should be 1 Sensor.category component", 1, spacecraftBus.findComponentByType(Sensor.class).size());
		assertEquals("There should be 1 FractalSensorArray.type component", 1, spacecraftBus.findComponentByType(FractalSensorArray.class).size());
		assertEquals("There should be 1 FractalSensorArray.category component", 1, spacecraftBus.findComponentByType(FractalSensorArray.class).size());
		assertEquals("There should be 1 LinearSensorArray.type component", 0, spacecraftBus.findComponentByType(LinearSensorArray.class).size());
		assertEquals("There should be 1 SystemComputer.category component", 1, spacecraftBus.findComponentByType(SystemComputer.class).size());
		assertEquals("There should be 1 BasicSystemComputer.type component", 1, spacecraftBus.findComponentByType(BasicSystemComputer.class).size());
	}

	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}


}
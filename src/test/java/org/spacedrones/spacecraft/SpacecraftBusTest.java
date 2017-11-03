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
	private Bus spacecraftBus = new SpacecraftBus();
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", "", hull, spacecraftBus);

	@Before
	public void setUp() {
		
	}

	@Test
	public void testSpacecraftBus() {
		assertEquals("Category for bus incorrect", Bus.category, spacecraftBus.category());
		
		assertEquals("There should be no bus components", 0, spacecraftBus.getComponents().size());
		assertNull(spacecraftBus.getSystemComputer());
		
		SystemComputer computer = ComputerFactory.getSystemComputer(BasicSystemComputer.type);
		spacecraftBus.register(computer);
		assertNotNull(spacecraftBus.getSystemComputer());
		
		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.type, SensorType.OPTICAL, 1);
		spacecraftBus.register(fractalSensor);


		assertEquals("There should be 2 bus components", 2, spacecraftBus.getComponents().size());
		
		assertEquals("There should be 1 Sensor.category component", 1, spacecraftBus.findComponentByCategory(Sensor.category).size());
		assertEquals("There should be 1 FractalSensorArray.type component", 1, spacecraftBus.findComponentByType(FractalSensorArray.type).size());
		assertEquals("There should be 1 FractalSensorArray.category component", 1, spacecraftBus.findComponentByCategory(FractalSensorArray.category).size());
		assertEquals("There should be 1 LinearSensorArray.type component", 0, spacecraftBus.findComponentByType(LinearSensorArray.type).size());
		assertEquals("There should be 1 SystemComputer.category component", 1, spacecraftBus.findComponentByCategory(SystemComputer.category).size());
		assertEquals("There should be 1 BasicSystemComputer.type component", 1, spacecraftBus.findComponentByType(BasicSystemComputer.type).size());
	}

	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}
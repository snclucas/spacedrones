package org.spacedrones.spacecraft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.sensors.FractalSensorArray;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorFactory;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

public class SpacecraftBusTest {
	
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", hull);
	Bus spacecraftBus = new SpacecraftBus("spacecraftBus", spacecraft);
	
	
	@Before
	public void setUp() {
		
	}
	

	@Test
	public void testSpacecraftBus() {
		assertEquals("Spacecraft bus name incorrect", "spacecraftBus", spacecraftBus.getName());
		assertEquals("Category for bus incorrect", Bus.category, spacecraftBus.getCategory());
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		assertEquals("There should be no bus components", 0, spacecraftBus.getComponents().size());
		assertNull(spacecraftBus.getSystemComputer());
		
		assertEquals("Spacecraft for bus incorrect", spacecraft, spacecraftBus.getSpacecraft());
		
		SystemComputer computer = ComputerFactory.getComputer(BasicSystemComputer.type());
		spacecraftBus.addComponent(computer);
		assertNotNull(spacecraftBus.getSystemComputer());
		
		Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.type(), Sensor.OPTICAL, 1);
		spacecraftBus.addComponent((SpacecraftBusComponent)fractalSensor);

		assertEquals("There should be 2 bus components", 2, spacecraftBus.getComponents().size());
		
		assertEquals("There should be 1 Sensor.category component", 1, spacecraftBus.findComponentByCategory(Sensor.category()).size());
		assertEquals("There should be 1 FractalSensorArray.type component", 1, spacecraftBus.findComponentByType(FractalSensorArray.type()).size());
		assertEquals("There should be 1 FractalSensorArray.category component", 1, spacecraftBus.findComponentByCategory(FractalSensorArray.category()).size());
		assertEquals("There should be 1 LinearSensorArray.type component", 0, spacecraftBus.findComponentByType(LinearSensorArray.type()).size());
		assertEquals("There should be 1 SystemComputer.category component", 1, spacecraftBus.findComponentByCategory(SystemComputer.category()).size());
		assertEquals("There should be 1 BasicSystemComputer.type component", 1, spacecraftBus.findComponentByType(BasicSystemComputer.type()).size());
	}
	
	
	
	
	
	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}

package org.spacedrones.spacecraft;

import org.junit.Test;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.propulsion.Engine;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpacecraftFirmwareTest {

	@Test
	public void testSpacecraftFirmware() {
		SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());
    SpacecraftBus spacecraftBus = new SpacecraftBus(systemComputer);

		boolean hasComputer = SpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		assertEquals(true, hasComputer);

    List<SystemComputer> computers = SpacecraftFirmware.getComputers(spacecraftBus);
    assertEquals("Number of computers should be 1", 1, computers.size());

		List<CommunicationComponent> commComponents = SpacecraftFirmware.getCommunicationDevices(spacecraftBus);
		assertEquals("Number of communication components should be 0", 0, commComponents.size());

		List<Engine> engines = SpacecraftFirmware.getEngines(spacecraftBus);
		assertEquals("Number of engines should be 0", 0, engines.size());

    computers = SpacecraftFirmware.getComputers(spacecraftBus);
    assertEquals("Number of computers should be 1", 1, computers.size());


		hasComputer = SpacecraftFirmware.bootstrapSystemComputer(spacecraftBus);
		assertEquals(true, hasComputer);

		computers = SpacecraftFirmware.getComputers(spacecraftBus);
		assertEquals("Number of computers should be 1", 1, computers.size());


		List<SpacecraftBusComponent> components = SpacecraftFirmware.findBusComponentByType(spacecraftBus, SystemComputer.class);
		SpacecraftBusComponent component = components.get(0);
		assertEquals("", component, systemComputer);

	}

}
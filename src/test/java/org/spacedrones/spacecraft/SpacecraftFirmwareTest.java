package org.spacedrones.spacecraft;

import org.junit.Test;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.propulsion.Engine;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpacecraftFirmwareTest {

	private List<SpacecraftBusComponent> components = new ArrayList<>();

	@Test
	public void testSpacecraftFirmware() {
		SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());
    components.add(systemComputer);

		boolean hasComputer = SpacecraftFirmware.bootstrapSystemComputer(components);
		assertEquals(true, hasComputer);

    List<SystemComputer> computers = SpacecraftFirmware.getComputers(components);
    assertEquals("Number of computers should be 1", 1, computers.size());

		List<CommunicationComponent> commComponents = SpacecraftFirmware.getCommunicationDevices(components);
		assertEquals("Number of communication components should be 0", 0, commComponents.size());

		List<Engine> engines = SpacecraftFirmware.getEngines(components);
		assertEquals("Number of engines should be 0", 0, engines.size());

    computers = SpacecraftFirmware.getComputers(components);
    assertEquals("Number of computers should be 1", 1, computers.size());


		hasComputer = SpacecraftFirmware.bootstrapSystemComputer(components);
		assertEquals(true, hasComputer);

		computers = SpacecraftFirmware.getComputers(components);
		assertEquals("Number of computers should be 1", 1, computers.size());


		List<SystemComputer> comps = SpacecraftFirmware.findBusComponentByType(components, SystemComputer.class);
		SpacecraftBusComponent component = comps.get(0);
		assertEquals("", component, systemComputer);

	}

}
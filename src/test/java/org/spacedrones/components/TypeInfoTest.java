package org.spacedrones.components;

import org.junit.Test;
import org.spacedrones.TestObject;
import org.spacedrones.components.comms.RadioCommunicator;
import org.spacedrones.components.comms.SubSpaceCommunicator;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.SimpleSolarArray;
import org.spacedrones.universe.celestialobjects.*;
import org.spacedrones.universe.structures.SubspaceBeacon;

import static org.junit.Assert.assertEquals;

public class TypeInfoTest {

	@Test
	public void testTypeInfoInAllComponents() {
		// Test categories
		assertEquals("", new TypeInfo("CommunicationDevice"), RadioCommunicator.category);
		assertEquals("", new TypeInfo("CommunicationDevice"), SubSpaceCommunicator.category);
		
		


    assertEquals("", new TypeInfo("Star"), Star.type);
		assertEquals("", new TypeInfo("CelestialObject"), Star.category);
		assertEquals("", new TypeInfo("CelestialObject"), Planet.category);
		assertEquals("", new TypeInfo("CelestialObject"), Region.category);
		assertEquals("", new TypeInfo("CelestialObject"), Asteroid.category);
		assertEquals("", new TypeInfo("CelestialObject"), UnknownObject.category);
		assertEquals("", new TypeInfo("CelestialObject"), SubspaceBeacon.category);


    assertEquals("", "SubspaceBeacon", SubspaceBeacon.type.toString());
		assertEquals("", "CelestialObject", SubspaceBeacon.category.toString());
		
		
		assertEquals("", "RadioCommunicator", RadioCommunicator.type.toString());
    assertEquals("", "CommunicationDevice", RadioCommunicator.category.toString());

		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
    assertEquals("", "CommunicationDevice", SubSpaceCommunicator.category.toString());




    assertEquals("", "Computer", Computer.category.toString());
    assertEquals("", "Computer", Computer.type.toString());
    assertEquals("", "SystemComputer", SystemComputer.category.toString());
    assertEquals("", "SystemComputer", SystemComputer.type.toString());
    assertEquals("", "SystemComputer", BasicSystemComputer.category.toString());
		assertEquals("", "BasicSystemComputer", BasicSystemComputer.type.toString());

    assertEquals("", "SystemComputer", new BasicSystemComputer("Test", null,
						TestObject.getEmptyBusCompSpec(), 0.0).category().toString());

    assertEquals("", "BasicSystemComputer", new BasicSystemComputer("Test", null,
            TestObject.getEmptyBusCompSpec(), 0.0).type().toString());




		assertEquals("", "SimpleSolarArray", SimpleSolarArray.type.toString());
		
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
		
	}

}

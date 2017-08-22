package org.spacedrones.components;

import org.junit.Test;
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
		assertEquals("", new TypeInfo("CommunicationDevice"), RadioCommunicator.category());
		assertEquals("", new TypeInfo("CommunicationDevice"), SubSpaceCommunicator.category());
		
		
		assertEquals("", new TypeInfo("Computer"), Computer.category());
		assertEquals("", new TypeInfo("Computer"), SystemComputer.category());
		
		assertEquals("", new TypeInfo("CelestialObject"), Star.category());
		assertEquals("", new TypeInfo("CelestialObject"), Planet.category());
		assertEquals("", new TypeInfo("CelestialObject"), Region.category());
		assertEquals("", new TypeInfo("CelestialObject"), Asteroid.category());
		assertEquals("", new TypeInfo("CelestialObject"), UnknownObject.category());
		assertEquals("", new TypeInfo("CelestialObject"), SubspaceBeacon.category());
		
		
		
		assertEquals("", "SubspaceBeacon", SubspaceBeacon.typeInfo.toString());
		
		
		assertEquals("", "RadioCommunicator", RadioCommunicator.type().toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
		
		assertEquals("", "SystemComputer", BasicSystemComputer.type().toString());
		
		assertEquals("", "SimpleSolarArray", SimpleSolarArray.type().toString());
		
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
		assertEquals("", "SubSpaceCommunicator", SubSpaceCommunicator.type.toString());
		
	}

}

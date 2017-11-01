package org.spacedrones.spacecraft;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class SpacecraftTest {
	
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Test spacecraft", "1", hull, new SpacecraftBus());

	@Test
	public void testSpacecraft() {
		// Should fail to online (Critical status: No system computer found! Aborting spacecraft onlining.)
		SystemStatus systemStatus = spacecraft.online();
		
		assertEquals(false, spacecraft.isOnline());
		assertEquals(false, systemStatus.isOK());
		assertEquals(true, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());
		
		SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type);
		SpacecraftBuildManager.addComponent(spacecraft, systemComputer);
		
		systemStatus = spacecraft.online();
		
		assertEquals(true, spacecraft.isOnline());
		assertEquals(true, systemStatus.isOK());
		assertEquals(false, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());

		System.out.println(spacecraft.getMass(Unit.kg));

	}
	
	
	@Test
	public void testSpacecraftHullData() {
		
		//spacecraftBus.getE
		
	}
	
	
	@Rule
	public ExpectedException  thrown= ExpectedException .none();

	@Test(expected=InvalidParameterException.class)
	public void testNoSuchSpacecraft() {
		SpacecraftFactory.getSpacecraft("NoSuchSpacecraft");
	}
	

}

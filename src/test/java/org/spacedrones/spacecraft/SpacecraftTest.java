package org.spacedrones.spacecraft;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class SpacecraftTest {
	
	private Hull hull = HullFactory.getHull("Shuttle");

	@Test
	public void testSpacecraft() {
    Spacecraft spacecraft = SpacecraftFactory.getSpacecraft("Shuttle");

		// Should fail to online (Critical status: No system computer found! Aborting spacecraft onlining.)
		SystemStatus systemStatus = spacecraft.online();
		
		assertEquals(false, spacecraft.isOnline());
		assertEquals(false, systemStatus.isOK());
		assertEquals(true, systemStatus.hasCriticalMessages());
		assertEquals(false, systemStatus.hasWarningMessages());


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

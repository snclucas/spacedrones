package org.spacedrones.universe.celestialobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.structures.SubspaceBeacon;

public class SubspaceBeaconTest {
	
	
	
	@Test
	public void testDataRecord() {

		// Beacon coordinates
		BigDecimal b1x =  new BigDecimal(10 * Unit.Pc.value()); BigDecimal b1y =  new BigDecimal(50 * Unit.Pc.value()); BigDecimal b1z =  new BigDecimal(0 * Unit.Pc.value()); 
		
		SensorSignalResponseProfile sensorSignalResponseProfile = SensorSignalResponseLibrary.getStandardSignalResponseProfile(SensorSignalResponseLibrary.SUBSPACE_BEACON);
		SubspaceBeacon subspaceBeacon1 = new SubspaceBeacon("Test beacon 1", sensorSignalResponseProfile);
		
		assertEquals("Subspace beacon type incorrect", SubspaceBeacon.typeInfo, subspaceBeacon1.getType());
				
		subspaceBeacon1.getSensorSignalResponse();
		subspaceBeacon1.getSensorSignalResponseProfile();

		
	}
	

}

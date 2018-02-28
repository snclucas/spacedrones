package org.spacedrones.universe.celestialobjects;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.structures.SubspaceBeacon;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SubspaceBeaconTest {

	@Test
	public void testDataRecord() {

		// Beacon coordinates
		BigDecimal b1x =  new BigDecimal(10 * Unit.Pc.value()); BigDecimal b1y =  new BigDecimal(50 * Unit.Pc.value()); BigDecimal b1z =  new BigDecimal(0 * Unit.Pc.value()); 
		
		SensorSignalResponseProfile sensorSignalResponseProfile = SensorSignalResponseLibrary
						.getStandardSignalResponseProfileForObjectType(SensorSignalResponseLibrary.SUBSPACE_BEACON);
		SubspaceBeacon subspaceBeacon1 = new SubspaceBeacon(sensorSignalResponseProfile);
		
		assertEquals("Subspace beacon type incorrect", SubspaceBeacon.class.getSimpleName(), subspaceBeacon1.getClass().getSimpleName());
				
		subspaceBeacon1.getSensorSignalResponse();
		subspaceBeacon1.getSensorSignalResponseProfile();
	}

}

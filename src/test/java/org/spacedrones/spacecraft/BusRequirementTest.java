package org.spacedrones.spacecraft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.physics.Unit;

public class BusRequirementTest {

	@Test
	public void testBusRequirementDAO() {
		
		double powerRequirement = 1.5;
		double cpuThroughputRequirement = 4.5;
		
		BusRequirement busRequirement = new BusRequirement(powerRequirement, cpuThroughputRequirement);
		
		assertEquals("Power from bus requirement DAO incorrect",powerRequirement, busRequirement.getPowerRequirement(Unit.W), 0.001);
		assertEquals("CPU throughput from bus requirement DAO incorrect",cpuThroughputRequirement, busRequirement.getCPUThroughputRequirement(Unit.MFLOPs), 0.001);
		
		
		
		
		
		
		
	}
	

}

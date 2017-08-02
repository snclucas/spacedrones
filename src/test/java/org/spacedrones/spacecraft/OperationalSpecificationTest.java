package org.spacedrones.spacecraft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.physics.Unit;

public class OperationalSpecificationTest {
	
	@Test
	public void testOperationalSpecification() {
		
		double nominalPower = 1.5 * Unit.W.value();
		double nominalCPU = 4.5 * Unit.MFLOP.value();		
		double maxPower = 1.5 * Unit.W.value();
		double maxCPU = 4.5 * Unit.MFLOP.value();
		
		OperationalSpecification operationalSpecification = 
				new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(), 0.001);
				
		nominalPower = 10.5 * Unit.W.value();
		nominalCPU = 40.5 * Unit.MFLOP.value();
		maxPower = 10.5 * Unit.W.value();
		maxCPU = 40.5 * Unit.MFLOP.value();
		
		operationalSpecification.setNominalPower(nominalPower);
		operationalSpecification.setNominalCPUThroughout(nominalCPU);
		operationalSpecification.setMaximumOperationalPower(maxPower);
		operationalSpecification.setMaximumOperationalCPUThroughput(maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(), 0.001);
		
	}

}

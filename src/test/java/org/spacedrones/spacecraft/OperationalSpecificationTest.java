package org.spacedrones.spacecraft;

import org.junit.Test;
import org.spacedrones.physics.Unit;

import static org.junit.Assert.assertEquals;

public class OperationalSpecificationTest {
	
	@Test
	public void testOperationalSpecification() {
		
		double nominalPower = 1.5 * Unit.MW.value();
		double nominalCPU = 4.5 * Unit.MFLOPs.value();
		double maxPower = 1.5 * Unit.MW.value();
		double maxCPU = 4.5 * Unit.MFLOPs.value();
		
		OperationalSpecification operationalSpecification = 
				new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(Unit.W), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(Unit.MFLOPs), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(Unit.MFLOPs), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(Unit.MFLOPs), 0.001);
				
		nominalPower = 10.5 * Unit.MW.value();
		nominalCPU = 40.5 * Unit.MFLOPs.value();
		maxPower = 10.5 * Unit.MW.value();
		maxCPU = 40.5 * Unit.MFLOPs.value();
		
		operationalSpecification.setNominalPower(nominalPower);
		operationalSpecification.setNominalCPUThroughout(nominalCPU);
		operationalSpecification.setMaximumOperationalPower(maxPower);
		operationalSpecification.setMaximumOperationalCPUThroughput(maxCPU);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, operationalSpecification.getNominalPower(Unit.W), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, operationalSpecification.getNominalCPUThroughout(Unit.MFLOPs), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, operationalSpecification.getMaximumOperationalPower(Unit.MFLOPs), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, operationalSpecification.getMaximumOperationalCPUThroughput(Unit.MFLOPs), 0.001);
		
	}

}

package org.spacedrones.components.energygeneration;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;

import static org.junit.Assert.assertEquals;

public class SubspacePowerGeneratorTest {
	
	/*  */
	@Test
	public void testSubspacePowerExtractor() {
		
		double mass = 25.0;
		double volume = 1.0;
		double nominalPower = 100 * Unit.W.value(); 
		double nominalCPUThroughput = 10 * Unit.kFLOPs.value();
		double maxOutputPower = 100 * Unit.kW.value();
		
		double arrayArea = 1 * Unit.m.value() * 10 * Unit.m.value();
		double efficiency = 75 * Unit.percent.value();
		
		BusComponentSpecification busSpecs = new BusComponentSpecification(
				new PhysicalSpecification(mass, volume),
				new OperationalSpecification( nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));
		
		SubspacePowerExtractor subspacePowerGenerator = new 
				SubspacePowerExtractor("Test sub-ether generator", busSpecs, arrayArea, efficiency);
		assertEquals("Max output power of sunether generator incorrect", arrayArea*efficiency*10.0*Unit.kW.value(), 
				subspacePowerGenerator.getMaximumPowerOutput(), 0.001);

		assertEquals("SubspacePowerExtractor category incorrect", 
				SubspacePowerExtractor.category, subspacePowerGenerator.category());

		assertEquals("SubspacePowerExtractor type incorrect", 
				SubspacePowerExtractor.type, subspacePowerGenerator.type());

	}

}

package org.spacedrones.spacecraft;

import org.junit.Test;
import org.spacedrones.physics.Unit;

import static org.junit.Assert.assertEquals;

public class BusRequirementSpecificationTest {

	@Test
	public void testBusRequirementSpecificationEquality() {
		PhysicalSpecification physicalSpecification1 = new PhysicalSpecification(23, 12, 67, 45, 23);
		OperationalSpecification operationalSpecification1 = 
				new OperationalSpecification(23, 34, 45, 67);
		BusComponentSpecification busComponentSpecification1 = 
				new BusComponentSpecification(physicalSpecification1, operationalSpecification1);
		
		PhysicalSpecification physicalSpecification2 = new PhysicalSpecification(23, 12, 67, 45, 23);
		OperationalSpecification operationalSpecification2 = 
				new OperationalSpecification(23, 34, 45, 67);
		BusComponentSpecification busComponentSpecification2 = 
				new BusComponentSpecification(physicalSpecification2, operationalSpecification2);
		
		assertEquals("Specifications should be equal", busComponentSpecification1, busComponentSpecification2);
	}
	
	
	@Test
	public void testBusRequirementSpecification() {
		
		double mass = 1.5 * Unit.kg.value();
		double volume = 4.5 * Unit.m3.value();		
		double height = 1.5 * Unit.m.value();
		double width = 4.5 * Unit.m.value();
		double length = 1.5 * Unit.m.value();
		
		PhysicalSpecification physicalSpecification = new PhysicalSpecification(mass, volume, length, width, height);
		
		
		double nominalPower = 1.5 * Unit.W.value();
		double nominalCPU = 4.5 * Unit.MFLOPs.value();
		double maxPower = 1.5 * Unit.W.value();
		double maxCPU = 4.5 * Unit.MFLOPs.value();
		
		OperationalSpecification operationalSpecification = 
				new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU);
		
		
		BusComponentSpecification busComponentSpecification = 
				new BusComponentSpecification(physicalSpecification, operationalSpecification);
		
		
		assertEquals("Mass from physicalSpecification incorrect", mass, busComponentSpecification.getMass(Unit.kg), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, busComponentSpecification.getVolume(Unit.m3), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, busComponentSpecification.getLength(Unit.m), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, busComponentSpecification.getHeight(Unit.m), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, busComponentSpecification.getWidth(Unit.m), 0.001);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, busComponentSpecification.getNominalPower(Unit.W), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, busComponentSpecification.getNominalCPUThroughout(Unit.MFLOPs), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, busComponentSpecification.getMaximumOperationalPower(Unit.W), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, busComponentSpecification.getMaximumOperationalCPUThroughput(Unit.MFLOPs), 0.001);
				
		
		mass = 10.5 * Unit.kg.value();
		volume = 40.5 * Unit.m3.value();
		height = 10.5 * Unit.m.value();
		width = 40.5 * Unit.m.value();
		length = 10.5 * Unit.m.value();
		
		busComponentSpecification.setMass(mass);
		busComponentSpecification.setVolume(volume);
		busComponentSpecification.setHeight(height);
		busComponentSpecification.setWidth(width);
		busComponentSpecification.setLength(length);
		
		
		nominalPower = 10.5*Unit.W.value();
		nominalCPU = 40.5*Unit.MFLOPs.value();
		maxPower = 10.5*Unit.W.value();
		maxCPU = 40.5*Unit.MFLOPs.value();
		
		busComponentSpecification.setNominalPower(nominalPower);
		busComponentSpecification.setNominalCPUThroughout(nominalCPU);
		busComponentSpecification.setMaximumOperationalPower(maxPower);
		busComponentSpecification.setMaximumOperationalCPUThroughput(maxCPU);
		
		
		assertEquals("Mass from physicalSpecification incorrect", mass, busComponentSpecification.getMass(Unit.kg), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, busComponentSpecification.getVolume(Unit.m3), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, busComponentSpecification.getLength(Unit.m), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, busComponentSpecification.getHeight(Unit.m), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, busComponentSpecification.getWidth(Unit.m), 0.001);
		
		assertEquals("Nominal power from operationalSpecification incorrect", nominalPower, busComponentSpecification.getNominalPower(Unit.W), 0.001);
		assertEquals("Nominal CPU from operationalSpecification incorrect", nominalCPU, busComponentSpecification.getNominalCPUThroughout(Unit.MFLOPs), 0.001);
		assertEquals("Max power from operationalSpecification incorrect", maxPower, busComponentSpecification.getMaximumOperationalPower(Unit.W
		), 0.001);
		assertEquals("Max CPU from operationalSpecification incorrect", maxCPU, busComponentSpecification.getMaximumOperationalCPUThroughput(Unit.MFLOPs), 0.001);
	}
	

}

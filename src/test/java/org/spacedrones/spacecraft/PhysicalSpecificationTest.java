package org.spacedrones.spacecraft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.physics.Unit;

public class PhysicalSpecificationTest {
	
	@Test
	public void testPhysicalSpecification() {
		
		double mass = 1.5 * Unit.kg.value();
		double volume = 4.5 * Unit.m3.value();		
		double height = 1.5 * Unit.m.value();
		double width = 4.5 * Unit.m.value();
		double length = 1.5 * Unit.m.value();
		
		PhysicalSpecification physicalSpecification = new PhysicalSpecification(mass, volume, length, width, height);
		
		assertEquals("Mass from physicalSpecification incorrect", mass, physicalSpecification.getMass(), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, physicalSpecification.getVolume(), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, physicalSpecification.getLength(), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, physicalSpecification.getHeight(), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, physicalSpecification.getWidth(), 0.001);
				
		mass = 10.5 * Unit.kg.value();
		volume = 40.5 * Unit.m3.value();
		height = 10.5 * Unit.m.value();
		width = 40.5 * Unit.m.value();
		length = 10.5 * Unit.m.value();
		
		physicalSpecification.setMass(mass);
		physicalSpecification.setVolume(volume);
		physicalSpecification.setHeight(height);
		physicalSpecification.setWidth(width);
		physicalSpecification.setLength(length);
		
		assertEquals("Mass from physicalSpecification incorrect", mass, physicalSpecification.getMass(), 0.001);
		assertEquals("Volume from physicalSpecification incorrect", volume, physicalSpecification.getVolume(), 0.001);
		assertEquals("Length from physicalSpecification incorrect", length, physicalSpecification.getLength(), 0.001);
		assertEquals("Height from physicalSpecification incorrect", height, physicalSpecification.getHeight(), 0.001);
		assertEquals("Width from physicalSpecification incorrect", width, physicalSpecification.getWidth(), 0.001);
	}

}

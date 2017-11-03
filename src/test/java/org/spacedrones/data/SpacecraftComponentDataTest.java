package org.spacedrones.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;

public class SpacecraftComponentDataTest {

	@Test
	public void testSpacecraftComponentData() {
		
		double mass = 1.5 * Unit.kg.value();
		double volume = 4.5 * Unit.m3.value();		
		double height = 1.5 * Unit.m.value();
		double width = 4.5 * Unit.m.value();
		double length = 1.5 * Unit.m.value();
		
		double nominalPower = 1.5 * Unit.W.value();
		double nominalCPU = 4.5 * Unit.MFLOPs.value();
		double maxPower = 1.5 * Unit.W.value();
		double maxCPU = 4.5 * Unit.MFLOPs.value();
		
		double[] values = new double[]{100.34, 34.45};
		
		SpacecraftComponentData spacecraftComponentData = new SpacecraftComponentData(new BusComponentSpecification(
				new PhysicalSpecification(mass, volume, length, width, height), new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU)),  values);
		
		
		assertEquals("BusComponentSpecification incorrect", mass, spacecraftComponentData.getBusComponentSpecification().getMass(Unit.kg), 0.0001);
		
		assertEquals("Values incorrect", values, spacecraftComponentData.getValues());
		assertEquals("Value 2 incorrect", values[1], spacecraftComponentData.getValues(1), 0.0001);
		assertEquals("Size of values incorrect", values.length, spacecraftComponentData.size(), 0.0001);
	}

}

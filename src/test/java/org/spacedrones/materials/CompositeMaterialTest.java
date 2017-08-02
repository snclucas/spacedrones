package org.spacedrones.materials;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompositeMaterialTest {
	
	

	@Test
	public void testCompositeMaterialComposition() {
		Element element1 = new Element(10,10,10);
		Element element2 = new Element(20,20,20);
		
		double[] fractions = new double[]{0.5, 0.5};
		
		Element[] elements = new Element[]{element1, element2};
		
		double expectedDensity = (element1.getDensity() + element2.getDensity())/2.0;
		double expectedAtomicNumber = (element1.getAtomicNumber() + element2.getAtomicNumber())/2.0;
		double expectedMassNumber = (element1.getMassNumber() + element2.getMassNumber())/2.0;
		
		CompositeMaterial compositeMaterial = new CompositeMaterial(elements, fractions, 1.0, 1.0, 1.0, 1.0);

		assertEquals("Density of composity material calculated incorrectly", 
				expectedDensity, compositeMaterial.getDensity(), 0.001);
		assertEquals("Atomic number of composity material calculated incorrectly", 
				expectedAtomicNumber, compositeMaterial.getAtomicNumber(), 0.001);
		assertEquals("Mass number of composity material calculated incorrectly", 
				expectedMassNumber, compositeMaterial.getMassNumber(), 0.001);
	}
	
	
	
	@Test
	public void testCompositeMaterialResistanceValues() {
		double impactResistance = 0.56;
		double emResistance = 0.36;
		double thermalResistance = 0.96;
		double radiationResistance = 0.16;
		
		Element element1 = new Element(10,10,10);
		Element element2 = new Element(20,20,20);
		
		double[] fractions = new double[]{0.5, 0.5};
		
		Element[] elements = new Element[]{element1, element2};
		
		
		CompositeMaterial compositeMaterial = new CompositeMaterial(elements, fractions, impactResistance, emResistance, thermalResistance, radiationResistance);

		assertEquals("Impact resistance not set correctly", 
				impactResistance, compositeMaterial.getImpactResistance(), 0.001);
		assertEquals("EM resistance not set correctly", 
				emResistance, compositeMaterial.getEMResistance(), 0.001);
		assertEquals("Thermal resistance not set correctly", 
				thermalResistance, compositeMaterial.getThermalResistance(), 0.001);
		assertEquals("Radiation resistance not set correctly", 
				radiationResistance, compositeMaterial.getRadiationResistance(), 0.001);
	}

}

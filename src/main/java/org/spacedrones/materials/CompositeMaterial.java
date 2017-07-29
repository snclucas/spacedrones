package org.spacedrones.materials;

public class CompositeMaterial extends AbstractMaterial {

	public CompositeMaterial(
			Element[] elements, 
			double[] fractions, 
			double impactResistance, 
			double emResistance, 
			double thermalResistance, 
			double radiationResistance) {
		super(
				elements, 
				fractions, 
				impactResistance, 
				emResistance, 
				thermalResistance, 
				radiationResistance);
	}

}

package org.spacedrones.materials;

import org.spacedrones.components.TypeInfo;

public abstract class AbstractMaterial implements Material {
	
	TypeInfo DENSITY = new TypeInfo("DENSITY");
	TypeInfo MASS_NUMBER = new TypeInfo("MASS_NUMBER");
	TypeInfo ATOMIC_NUMBER = new TypeInfo("ATOMIC_NUMBER");
	
	TypeInfo THERMAL_RESISTANCE = new TypeInfo("THERMAL_RESISTANCE");
	TypeInfo RADIATION_RESISTANCE = new TypeInfo("RADIATION_RESISTANCE");
	TypeInfo EM_RESISTANCE = new TypeInfo("EM_RESISTANCE");
	TypeInfo IMPACT_RESISTANCE = new TypeInfo("IMPACT_RESISTANCE");

	private double thermalResistanceCoefficient;
	private double radiationResistanceCoefficient;
	private double emResistanceCoefficient;
	private double impactResistanceCoefficient;
	
	private Element[] elements;
	private double[] fractions;
	
	
	public AbstractMaterial(
			Element[] elements, 
			double[] fractions, 
			double impactResistanceCoefficient,
			double emResistanceCoefficient,
			double thermalResistanceCoefficient,
			double radiationResistanceCoefficient) {
		super();
		this.elements = elements;
		this.fractions = fractions;
		this.impactResistanceCoefficient = impactResistanceCoefficient;
		this.emResistanceCoefficient = emResistanceCoefficient;
		this.thermalResistanceCoefficient = thermalResistanceCoefficient;
		this.radiationResistanceCoefficient = radiationResistanceCoefficient;
	}


	
	@Override
	public double getEMResistance() {
		return this.emResistanceCoefficient;
	}



	@Override
	public double getImpactResistance() {
		return this.impactResistanceCoefficient;
	}
	
	


	@Override
	public double getRadiationResistance() {
		return this.radiationResistanceCoefficient;
	}



	@Override
	public double getThermalResistance() {
		return this.thermalResistanceCoefficient;
	}



	@Override
	public double getMassNumber() {
		return calculateFraction(MASS_NUMBER);
	}


	@Override
	public double getAtomicNumber() {
		return calculateFraction(ATOMIC_NUMBER);
	}


	@Override
	public double getDensity() {
		return calculateFraction(DENSITY);
	}
	
	
	private double calculateFraction(TypeInfo quantity) {
		double value = 0.0;		
		for(int i = 0; i< fractions.length; i++) {
			if(quantity == DENSITY)
				value  += fractions[i]*elements[i].getDensity();
			else if(quantity == MASS_NUMBER)
				value  += fractions[i]*elements[i].getMassNumber();
			else
				value  += fractions[i]*elements[i].getAtomicNumber();
		}
		return value;
	}

}

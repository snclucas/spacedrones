package org.spacedrones.materials;

import java.util.*;

public abstract class AbstractMaterial implements Material {

	String DENSITY = "DENSITY";
  String MASS_NUMBER = "MASS_NUMBER";
  String ATOMIC_NUMBER = "ATOMIC_NUMBER";

  String THERMAL_RESISTANCE = "THERMAL_RESISTANCE";
  String RADIATION_RESISTANCE = "RADIATION_RESISTANCE";
  String EM_RESISTANCE = "EM_RESISTANCE";
  String IMPACT_RESISTANCE = "IMPACT_RESISTANCE";

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


	private double calculateFraction(String quantity) {
		double value = 0.0;
		for(int i = 0; i< fractions.length; i++) {
			if(Objects.equals(quantity, DENSITY))
				value  += fractions[i]*elements[i].getDensity();
			else if(Objects.equals(quantity, MASS_NUMBER))
				value  += fractions[i]*elements[i].getMassNumber();
			else
				value  += fractions[i]*elements[i].getAtomicNumber();
		}
		return value;
	}

}

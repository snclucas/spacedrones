package org.spacedrones.materials;


public interface Material extends Substance {
	double getAtomicNumber();
	double getMassNumber();

	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
}

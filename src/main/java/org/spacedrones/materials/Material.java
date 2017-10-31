package org.spacedrones.materials;


import org.spacedrones.components.TypeInfo;

public interface Material {
	TypeInfo category = new TypeInfo("Material");
	TypeInfo type = category;

	double getAtomicNumber();
	double getMassNumber();
	double getDensity();
	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
}

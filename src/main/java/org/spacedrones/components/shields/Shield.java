package org.spacedrones.components.shields;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.spacecraft.BusRequirement;

public interface Shield extends SpacecraftBusComponent {

	String IMPACT_SHIELD = "IMPACT_SHIELD";
	String EM_SHIELD = "EM_SHIELD";
	String THERMAL_SHIELD = "THERMAL_SHIELD";
	String RADIATION_SHIELD = "RADIATION_SHIELD";
	
	/* Get the various resistances */
	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
	
	/* Set the shield power level */
	BusRequirement setShieldLevel(double powerLevel);
}

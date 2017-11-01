package org.spacedrones.structures.hulls;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.PhysicalComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;

public interface Hull extends PhysicalComponent, BusCommunicator {
	TypeInfo category = new TypeInfo("Hull");
	TypeInfo type = category;

	enum Type {
    RECTANGULAR("RECTANGULAR"),
    SPHEROID("SPHEROID");

    Type(String typeName) {
			this.typeName = typeName;
		}

		private final String typeName;

		public String getType() {
			return typeName;
		}
	}
	
	//Hull mass fraction is the fraction of the hull thickness that is solid
	double HULL_VOLUME_FRACTION = 0.5;

	double getLength(Unit unit);
	double getWidth(Unit unit);
	double getHeight(Unit unit);
	double getThickness(Unit unit);

	double getImpactResistance();
	double getEMResistance();
	double getRadiationResistance();
	double getThermalResistance();
	
	double getImpactResistanceModifier();
	double getEmResistanceModifier();
	double getThermalResistanceModifier();
	double getRadiationResistanceModifier();

}

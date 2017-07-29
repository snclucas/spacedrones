package org.spacedrones.data;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.materials.Material;

public interface MaterialDataProvider {
	TypeInfo ALUMINUM = new TypeInfo("Aluminum");
	TypeInfo TITANIUM = new TypeInfo("Titanium");
	TypeInfo REINFORCED_TITANIUM = new TypeInfo("Reinforced titanium");
	
	Material getMaterial(TypeInfo materialType);
}

package org.spacedrones.data;

import org.spacedrones.materials.Material;

public interface MaterialDataProvider {
	String ALUMINUM = "Aluminum";
  String TITANIUM = "Titanium";
  String REINFORCED_TITANIUM = "Reinforced titanium";

	Material getMaterial(String materialType);
}

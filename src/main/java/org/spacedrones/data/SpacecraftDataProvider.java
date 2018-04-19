package org.spacedrones.data;

import org.spacedrones.consumables.FuelFactory;
import org.spacedrones.materials.Gas;
import org.spacedrones.materials.Liquid;

public interface SpacecraftDataProvider extends SpacecraftComponentDataProvider, FuelFactory {

  Liquid getLiquid(int liquidType);
  Gas getGas(int gasType);

}

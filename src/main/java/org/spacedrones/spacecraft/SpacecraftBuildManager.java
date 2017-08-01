package org.spacedrones.spacecraft;

import org.spacedrones.components.SpacecraftBusComponent;

public class SpacecraftBuildManager {

  public static void addComponent(Spacecraft spacecraft, SpacecraftBusComponent component) {
    ((AbstractSpacecraft)spacecraft).addComponent(component);
  }

	

}

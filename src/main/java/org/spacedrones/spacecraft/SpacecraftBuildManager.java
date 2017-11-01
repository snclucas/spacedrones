package org.spacedrones.spacecraft;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.game.Manager;

public class SpacecraftBuildManager implements Manager{

  private Bus bus;

  public SpacecraftBuildManager() {



  }

  public Spacecraft newSpacecraft() {

  }

  public void receiveHandle(AbstractSpacecraft.Handle handle) {
    this.bus = handle.getBus();
  }

  public void addComponent(SpacecraftBusComponent component) {
    bus.register(component);
  }




  @Override
  public void tick(final double dt) {

  }
}

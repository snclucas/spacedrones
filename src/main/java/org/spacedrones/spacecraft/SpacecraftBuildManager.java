package org.spacedrones.spacecraft;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.*;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.hulls.Hull;

public final class SpacecraftBuildManager implements SpacecraftManager {

  private Bus bus;

  private Hull hull;

  private Spacecraft spacecraft;

  private double maxVolumeForComponents;

  private double hullVolumeRemaining;

  public SpacecraftBuildManager(String name, Hull hull) {
    SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.type);
    String ident = Configuration.getUUID();
    Bus spacecraftBus = new SpacecraftBus(systemComputer);
    spacecraft = new SimpleSpacecraft(name, ident, hull, spacecraftBus);
    spacecraft.giveManagerHandleTo(this);

    maxVolumeForComponents = hull.getVolume(Unit.m3);
    hullVolumeRemaining = maxVolumeForComponents;
  }

  public SpacecraftBuildManager(Spacecraft spacecraft) {
    spacecraft.giveManagerHandleTo(this);
    maxVolumeForComponents = hull.getVolume(Unit.m3);
    hullVolumeRemaining = maxVolumeForComponents;
  }

  public Spacecraft getSpacecraft(){
    return spacecraft;
  }

  public void receiveManagerHandle(AbstractSpacecraft.Handle handle) {
    this.bus = handle.getBus();
  }

  public void addComponent(SpacecraftBusComponent component) {
    double volumeOfComponent = component.getVolume(Unit.m3);
    if(hullVolumeRemaining >= volumeOfComponent) {
      bus.register(component);
      hullVolumeRemaining -= volumeOfComponent;
    }
    else {
      throw new ComponentConfigurationException("Insufficient volume for component. "
              + component.getVolume(Unit.m3) + " needed, " + hullVolumeRemaining + " remaining.");
    }
  }

  public void removeComponent(SpacecraftBusComponent component) {
    if(bus.getComponents().contains(component)) {
      bus.getComponents().remove(component);
      hullVolumeRemaining += component.getVolume(Unit.m3);
    }
    else {
      throw new ComponentConfigurationException("Component not found, cannot remove it from spacecraft.");
    }
  }

}

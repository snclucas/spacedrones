package org.spacedrones.spacecraft;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.hulls.Hull;

import java.util.ArrayList;
import java.util.List;

public final class SpacecraftBuildManager implements SpacecraftManager {

  private Hull hull;

  private Spacecraft spacecraft;

  private double maxVolumeForComponents;

  private double hullVolumeRemaining;

  private List<SpacecraftBusComponent> components = new ArrayList<>();

  public SpacecraftBuildManager(String name, Hull hull) {
    SystemComputer systemComputer = ComputerFactory.getSystemComputer("BasicSystemComputer");
    String ident = Configuration.getUUID();
    spacecraft = new SimpleSpacecraft(name, ident, hull);
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
    this.components = handle.getSCComponents();
  }

  public void addComponent(SpacecraftBusComponent component) {
    double volumeOfComponent = component.getVolume(Unit.m3);
    if(hullVolumeRemaining >= volumeOfComponent) {
      hullVolumeRemaining -= volumeOfComponent;
      components.add(component);
    }
    else {
      throw new ComponentConfigurationException("Insufficient volume for component. "
              + component.getVolume(Unit.m3) + " needed, " + hullVolumeRemaining + " remaining.");
    }
  }

  public void removeComponent(SpacecraftBusComponent component) {
    if(components.contains(component)) {
      components.remove(component);
      hullVolumeRemaining += component.getVolume(Unit.m3);
    }
    else {
      throw new ComponentConfigurationException("Component not found, cannot remove it from spacecraft.");
    }
  }

}

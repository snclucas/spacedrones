package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.*;

import java.util.List;

public interface Bus extends BusCommunicator, StatusProvider {
  List<SpacecraftBusComponent> findComponentByType(Class<? extends SpacecraftBusComponent> component);
  List<SpacecraftBusComponent> getComponents();
  void register(SpacecraftBusComponent spacecraftBusComponent);
  SystemComputer getSystemComputer();
}

package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.*;

import java.util.List;

public interface Bus extends BusCommunicator, Taxonomic, StatusProvider {
  TypeInfo category = new TypeInfo("Bus");
  TypeInfo type = category;

  List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType);
  List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory);
  List<SpacecraftBusComponent> getComponents();

  void register(SpacecraftBusComponent spacecraftBusComponent);

  SystemStatusMessage registerSystemComputer(SystemComputer computer);

  SystemComputer getSystemComputer();
}

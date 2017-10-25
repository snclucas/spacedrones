package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.SystemStatusMessage;

import java.util.List;

public interface Bus extends BusCommunicator {
  List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType);
  List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory);
  List<SpacecraftBusComponent> getComponents();
  
  void register(SpacecraftBusComponent spacecraftBusComponent);

  SystemStatusMessage registerSystemComputer(SystemComputer computer);

  SystemComputer getSystemComputer();

  //Spacecraft getSpacecraft();
  //void setSpacecraft(Spacecraft spacecraft);
}

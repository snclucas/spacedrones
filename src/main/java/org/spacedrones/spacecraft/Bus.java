package org.spacedrones.spacecraft;

import java.util.List;

import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.ComponentVisitor;
import org.spacedrones.components.Identifiable;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.SystemStatusMessage;

public interface Bus extends BusCommunicator, ComponentVisitor, Identifiable {

  TypeInfo category = new TypeInfo("Bus");

  List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType);
  List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory);
  List<SpacecraftBusComponent> getComponents();

  void addComponent(SpacecraftBusComponent component);

  SystemStatusMessage registerSystemComputer(SystemComputer computer);

  SystemComputer getSystemComputer();

  Spacecraft getSpacecraft();
  //void setSpacecraft(Spacecraft spacecraft);
}

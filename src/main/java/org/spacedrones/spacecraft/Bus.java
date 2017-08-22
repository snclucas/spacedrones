package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.SystemStatusMessage;

import java.util.List;

public interface Bus extends BusCommunicator, ComponentVisitor, Identifiable {

  TypeInfo category = new TypeInfo("Bus");

  List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType);
  List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory);
  List<SpacecraftBusComponent> getComponents();

  PhysicalSpecification getPhysicalSpecification();

  void addComponent(SpacecraftBusComponent component);

  SystemStatusMessage registerSystemComputer(SystemComputer computer);

  SystemComputer getSystemComputer();

  Spacecraft getSpacecraft();
  void setSpacecraft(Spacecraft spacecraft);
}

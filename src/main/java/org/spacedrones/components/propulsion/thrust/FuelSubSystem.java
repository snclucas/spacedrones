package org.spacedrones.components.propulsion.thrust;


import org.spacedrones.components.*;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.storage.fuel.*;

public interface FuelSubSystem extends SpacecraftBusComponent, BusCommunicator {
  double getFuelFlowRate(double powerLevel, Unit unit);
  void setFuelFlowRate(double flowRate, Unit unit);
  void setFuelTank(FuelStorageTank fuelStorageTank);
}

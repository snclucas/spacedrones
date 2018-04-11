package org.spacedrones.components.propulsion.thrust;


import org.spacedrones.components.*;
import org.spacedrones.structures.storage.fuel.*;

public interface FuelSubSystem extends SpacecraftBusComponent, BusCommunicator {
  double getFuelFlowRate(double powerLevel);
  void setFuelFlowRate(double flowRate);
  void setFuelTank(FuelStorageTank fuelStorageTank);
}

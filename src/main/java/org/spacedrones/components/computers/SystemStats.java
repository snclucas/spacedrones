package org.spacedrones.components.computers;


import org.spacedrones.physics.Unit;

import java.util.HashMap;
import java.util.Map;

public class SystemStats {

  private final Map<String, Double> stats = new HashMap<>();
  final Unit powerUnit;
  final Unit cpuUnit;

  public SystemStats(double currentPower, double currentCPU,
                     double maxPower, double maxCPU,
                     double maxPowerAvailable, double maxCPUAvailable,
                     Unit powerUnit, Unit cpuUnit) {

    this.powerUnit = powerUnit;
    this.cpuUnit = cpuUnit;

    stats.put("Current power requirement", currentPower);
    stats.put("Current CPU requirement", currentCPU);
    stats.put("Max. power requirement", maxPower);
    stats.put("Max. CPU requirement", maxCPU);

    stats.put("Max. power available", maxPowerAvailable);
    stats.put("Max. CPU available", maxCPUAvailable);
  }

  public double getCurrentPower() {
    return stats.get("Current power requirement");
  }

  public void setCurrentPower(final double currentPower) {
    stats.put("Current power requirement", currentPower);
  }

  public double getCurrentCPU() {
    return stats.get("Current CPU requirement");
  }

  public void setCurrentCPU(final double currentCPU) {
    stats.put("Current CPU requirement", currentCPU);
  }

  public double getMaxPower() {
    return stats.get("Max. power requirement");
  }

  public void setMaxPower(final double maxPower) {
    stats.put("Max. power requirement", maxPower);
  }

  public double getMaxCPU() {
    return stats.get("Max. CPU requirement");
  }

  public void setMaxCPU(final double maxCPU) {
    stats.put("Max. CPU requirement", maxCPU);
  }

  public double getMaxPowerAvailable() {
    return stats.get("Max. power available");
  }

  public void setMaxPowerAvailable(final double maxPowerAvail) {
    stats.put("Max. power available", maxPowerAvail);
  }

  public double getMaxCPUAvailable() {
    return stats.get("Max. CPU available");
  }

  public void setMaxCPUAvailable(final double maxCPUAvail) {
    stats.put("Max. CPU available", maxCPUAvail);
  }

  @Override
  public String toString() {
    return "Current power: " + getCurrentPower() + " " + powerUnit.symbol() + System.lineSeparator() +
            "Current CPU: " + getCurrentCPU() + " " + cpuUnit.symbol() + System.lineSeparator() +
            "Max. power: " + getMaxPower() + " " + powerUnit.symbol() + System.lineSeparator() +
            "Max. CPU: " + getMaxCPU() + " " + cpuUnit.symbol() + System.lineSeparator() +
            "Max. power available: " + getMaxPowerAvailable() + " " + powerUnit.symbol() + System.lineSeparator() +
            "Max. CPU available: " + getMaxCPUAvailable() + " " + cpuUnit.symbol() + System.lineSeparator();
  }
}

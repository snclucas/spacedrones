package org.spacedrones.universe.celestialobjects;

public enum StarClass {
  O("O"),
  B("B"),
  A("A"),
  F("F"),

  G0V("G0V"),
  G1V("G1V"),
  G2V("G2V"),
  G3V("G3V"),
  G4V("G4V"),
  G5V("G5V"),
  G6V("G6V"),
  G7V("G7V"),
  G8V("G8V"),
  G9V("G9V"),

  K0V("K0V"),
  K1V("K1V"),
  K2V("K2V"),
  K3V("K3V"),
  K4V("K4V"),
  K5V("K5V"),
  K6V("K6V"),
  K7V("K7V"),
  K8V("K8V"),
  K9V("K9V"),

  M("M");

  private final String starClass;

  StarClass(String starClass) {
    this.starClass = starClass;
  }

  public String getStarClass() {
    return starClass;
  }

}
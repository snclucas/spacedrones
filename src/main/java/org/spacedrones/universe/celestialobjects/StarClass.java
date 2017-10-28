package org.spacedrones.universe.celestialobjects;

public enum StarClass {
    O("O-class star"),
    B("B-class star"),
    A("A-class star"),
    F("F-class star"),
    G("G-class star"),
    K("K-class star"),
    M("M-class star");

    private final String starClass;

    StarClass(String starClass) {
      this.starClass = starClass;
    }

    public String getStarClass() {
      return starClass;
    }

}

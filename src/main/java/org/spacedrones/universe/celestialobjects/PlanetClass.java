package org.spacedrones.universe.celestialobjects;

public enum PlanetClass {
    M("Class M");

    private final String planetClass;

    PlanetClass(String planetClass) {
      this.planetClass = planetClass;
    }

    public String getPlanetClass() {
      return planetClass;
    }

}
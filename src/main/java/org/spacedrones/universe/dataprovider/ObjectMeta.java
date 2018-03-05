package org.spacedrones.universe.dataprovider;

import org.spacedrones.universe.celestialobjects.*;


public class ObjectMeta {
  public String id;
  public String name;
  CelestialObject celestialObject;

  ObjectMeta(final String id, final String name, final CelestialObject celestialObject) {
    this.id = id;
    this.name = name;
    this.celestialObject = celestialObject;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final ObjectMeta that = (ObjectMeta) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    return result;
  }
}

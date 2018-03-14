package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.*;
import org.spacedrones.universe.*;

import java.util.Arrays;


public class ObjectLocationMeta<T extends Taxonomic> {
  public final String id;
  public final String name;
  public Coordinates coordinates;
  public double[] velocity;
  public final T object;

  public ObjectLocationMeta(final String id, final String name, final Coordinates coordinates, double[] velocity, T object) {
    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
    this.velocity = velocity;
    this.object = object;
  }

  public ObjectLocationMeta(final String id, final String name, final Coordinates coordinates, T object) {
    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
    this.velocity = new double[]{0.0, 0.0, 0.0};
    this.object = object;
  }

  @Override
  public String toString() {
    return "ObjectLocationMeta{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", coordinates=" + coordinates +
            ", velocity=" + Arrays.toString(velocity) +
            ", object=" + object +
            '}';
  }
}

package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.*;
import org.spacedrones.universe.*;

import java.util.Arrays;


public class ObjectMeta<T extends Taxonomic> {
  public String id;
  public String name;
  public Coordinates coordinates;
  public double[] velocity;
  public T object;

  public ObjectMeta(ObjectMeta<T> objectMeta) {
    this.id = objectMeta.id;
    this.name = objectMeta.name;
    this.coordinates = objectMeta.coordinates;
    this.velocity = objectMeta.velocity;
    this.object = objectMeta.object;
  }

  public ObjectMeta(final String id, final String name, final Coordinates coordinates, double[] velocity, T object) {
    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
    this.velocity = velocity;
    this.object = object;
  }

  public ObjectMeta(final String id, final String name, final Coordinates coordinates, T object) {
    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
    this.velocity = new double[]{0.0, 0.0, 0.0};
    this.object = object;
  }

  @Override
  public String toString() {
    return "ObjectMeta{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", coordinates=" + coordinates +
            ", velocity=" + Arrays.toString(velocity) +
            ", object=" + object +
            '}';
  }
}

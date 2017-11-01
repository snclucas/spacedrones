package org.spacedrones.components;

public interface Taxonomic extends Identifiable {
  TypeInfo category();
  TypeInfo type();
}

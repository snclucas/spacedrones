package org.spacedrones.components;

public interface Component extends Identifiable {
  TypeInfo getCategory();
  TypeInfo getType();
}

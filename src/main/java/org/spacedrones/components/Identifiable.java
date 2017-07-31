package org.spacedrones.components;

public interface Identifiable {

	static TypeInfo type(){
    return new TypeInfo("");
  }

	static TypeInfo category() {
		return new TypeInfo("");
	}

  TypeInfo getCategory();
  TypeInfo getType();

	String getName();
	String getIdent();
	String describe();
}

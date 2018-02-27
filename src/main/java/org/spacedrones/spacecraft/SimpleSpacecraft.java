package org.spacedrones.spacecraft;

import org.spacedrones.structures.hulls.Hull;

public class SimpleSpacecraft extends AbstractSpacecraft {

	public SimpleSpacecraft(String name, String ident, Hull hull, Bus bus) {
		super(name, ident, hull, bus);
	}

	@Override
	public String description() {
		return "Simple spacecraft";
	}

}

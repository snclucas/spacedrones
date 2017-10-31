package org.spacedrones.spacecraft;

import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;

public class SimpleSpacecraft extends AbstractSpacecraft {

	public SimpleSpacecraft(String name, String ident, Hull hull, Bus bus) {
		super(name, ident, hull, bus);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
    return new SystemStatus(this);
	}

	@Override
	public String describe() {
		return "Simple spacecraft";
	}

}

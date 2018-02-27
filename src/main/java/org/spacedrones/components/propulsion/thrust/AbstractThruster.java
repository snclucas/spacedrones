package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractThruster extends AbstractThrustingFuelConsumingEngine {

	public AbstractThruster(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, maximumThrust, engineVector, vectored);
	}

}

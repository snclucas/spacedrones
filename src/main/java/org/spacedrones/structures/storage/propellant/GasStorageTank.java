package org.spacedrones.structures.storage.propellant;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class GasStorageTank extends AbstractFuelStorageTank {

	GasStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

}

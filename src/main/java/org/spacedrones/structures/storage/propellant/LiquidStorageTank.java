package org.spacedrones.structures.storage.propellant;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class LiquidStorageTank extends AbstractTank {

	LiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

}

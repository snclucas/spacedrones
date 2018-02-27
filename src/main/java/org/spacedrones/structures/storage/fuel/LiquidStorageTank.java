package org.spacedrones.structures.storage.fuel;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class LiquidStorageTank extends AbstractFuelStorageTank {

	LiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	@Override
	public String description() {
		return "Liquid storage tank, suitable for liquids and pressurised vapors.";
	}
	
	
}

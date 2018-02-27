package org.spacedrones.structures.storage.fuel;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class CryogenicLiquidStorageTank extends LiquidStorageTank {

	CryogenicLiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	@Override
	public String description() {
		return "Cryogenic liquid storage tank, suitable for gases liquefied at cryogenic temperatures.";
	}
	
}

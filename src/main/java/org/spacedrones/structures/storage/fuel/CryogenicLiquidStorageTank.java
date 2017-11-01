package org.spacedrones.structures.storage.fuel;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class CryogenicLiquidStorageTank extends LiquidStorageTank {
	public static TypeInfo type = new TypeInfo("CryogenicLiquidStorageTank");

	CryogenicLiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	@Override
	public TypeInfo type() {
		return type;
	}

	@Override
	public String describe() {
		return "Cryogenic liquid storage tank, suitable for gases liquefied at cryogenic temperatures.";
	}
	
}

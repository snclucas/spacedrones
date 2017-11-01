package org.spacedrones.structures.storage.fuel;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class LiquidStorageTank extends AbstractFuelStorageTank {
	public static TypeInfo type = new TypeInfo("LiquidStorageTank");


	public LiquidStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification, capacity);
	}

	
	@Override
	public TypeInfo type() {
		return type;
	}


	@Override
	public String describe() {
		return "Liquid storage tank, suitable for liquids and pressurised vapors.";
	}
	
	
}

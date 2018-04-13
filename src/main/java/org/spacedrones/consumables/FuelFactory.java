package org.spacedrones.consumables;

public interface FuelFactory {
	FuelConstituent getLiquid(int fuelType);
}

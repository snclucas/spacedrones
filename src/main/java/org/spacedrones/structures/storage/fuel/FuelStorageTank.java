package org.spacedrones.structures.storage.fuel;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.consumables.Fuel;

public interface FuelStorageTank extends SpacecraftBusComponent {
	
	TypeInfo categoryID = new TypeInfo("FuelStorageTank");

	double getCapacity();

	double getFuelLevel();

	void setFuel(Fuel fuel, double fuelVolume);
	
	void fillFuel(double fuelVolume);
	
	void removeFuel(double fuelVolume);
	
	Fuel getFuel();
	
	double getAmountOfFuelInTank();

}

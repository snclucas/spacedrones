package org.spacedrones.structures.storage.fuel;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.consumables.FuelConstituent;

public interface FuelStorageTank extends SpacecraftBusComponent {
	double getCapacity();

	double getFuelLevel();

	void setFuelConstituent(FuelConstituent fuelConstituent, double fuelVolume);
	
	void fillFuelConstituent(double fuelVolume);
	
	void removeFuelConstituent(double fuelVolume);

	FuelConstituent getFuelConstituent();
	
	double getAmountOfFuelInTank();

}

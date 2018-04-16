package org.spacedrones.structures.storage.propellant;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.consumables.FuelConstituent;

public interface Tank extends SpacecraftBusComponent {
	double getCapacity();

	double getLevel();

	void setFuelConstituent(FuelConstituent fuelConstituent, double fuelVolume);
	
	void fill(double volume);
	
	void empty(double volume);

	FuelConstituent getFuelConstituent();
	
	double getAmountOfFuelInTank();

}
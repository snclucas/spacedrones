package org.spacedrones.structures.storage.fuel;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.exceptions.NoFuelInTankException;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractFuelStorageTank extends AbstractBusComponent implements FuelStorageTank {

	protected double amountOfFuelInTank;
	protected double capacity;
	protected double fuelLevel;

	protected Fuel fuel;

	public AbstractFuelStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification);

		setCapacity(capacity);
		this.fuelLevel = 0.0;
	}


	@Override
	public TypeInfo getCategory() {
		return categoryID;
	}


	@Override
	public double getMass() {
		if(fuel != null)
			return super.getMass() + fuel.getDensity() * amountOfFuelInTank;
		else 
			return super.getMass();
	}


	public Fuel getFuel() {
		if(fuel != null)
			return fuel;
		else 
			throw new NoFuelInTankException("No fuel in tank [" + getName() + "]");
	}




	public void setFuel(Fuel fuel, double fuelVolume) {
		this.fuel = fuel;
		if(fuelVolume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank = fuelVolume;
	}



	@Override
	public void removeFuel(double fuelVolume) {
		if(fuelVolume < amountOfFuelInTank)
			amountOfFuelInTank -= fuelVolume;
		else 
			amountOfFuelInTank = 0.0;

	}




	public void fillFuel(double fuelVolume) {
		getFuel();
		if(fuelVolume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank += fuelVolume;
	}


	public double getAmountOfFuelInTank() {
		return this.amountOfFuelInTank;
	}




	@Override
	public double getFuelLevel() {
		return getAmountOfFuelInTank() / getCapacity();
	}






	public double getCapacity() {
		return capacity;
	}


	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}



	@Override
	public double getCurrentPower() {
		return getNominalPower();
	}



	@Override
	public double getCurrentCPUThroughput() {
		return getNominalCPUThroughput();
	}




	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void tick() {
	}
	
}

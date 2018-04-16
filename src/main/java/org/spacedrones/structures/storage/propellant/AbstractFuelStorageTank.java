package org.spacedrones.structures.storage.propellant;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.consumables.FuelConstituent;
import org.spacedrones.exceptions.NoFuelInTankException;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractFuelStorageTank extends AbstractBusComponent implements Tank {
	private double amountOfFuelInTank;
	private double capacity;
	private double fuelLevel;

	protected FuelConstituent fuel;

	public AbstractFuelStorageTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification);

		setCapacity(capacity);
		this.fuelLevel = 0.0;
	}

	@Override
	public double getMass(Unit unit) {
		if(fuel != null)
			return super.getMass(unit) + fuel.getDensity() * amountOfFuelInTank;
		else 
			return super.getMass(unit);
	}


	public FuelConstituent getFuelConstituent() {
		if(fuel != null)
			return fuel;
		else 
			throw new NoFuelInTankException("No fuel in tank [" + name() + "]");
	}




	public void setFuelConstituent(FuelConstituent fuel, double fuelVolume) {
		this.fuel = fuel;
		if(fuelVolume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank = fuelVolume;
	}



	@Override
	public void empty(double volume) {
		if(volume < amountOfFuelInTank)
			amountOfFuelInTank -= volume;
		else 
			amountOfFuelInTank = 0.0;

	}




	public void fill(double volume) {
		getFuelConstituent();
		if(volume >= capacity)
			amountOfFuelInTank = capacity;
		else
			amountOfFuelInTank += volume;
	}


	public double getAmountOfFuelInTank() {
		return this.amountOfFuelInTank;
	}




	@Override
	public double getLevel() {
		return getAmountOfFuelInTank() / getCapacity();
	}






	public double getCapacity() {
		return capacity;
	}


	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}



	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit);
	}



	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getNominalCPUThroughput(unit);
	}




	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void tick(double dt) {
	}
	
}

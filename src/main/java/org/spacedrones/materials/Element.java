package org.spacedrones.materials;

public class Element {

	protected double massNumber;
	protected double atomicNumber;
	protected double density;
	
	
	public Element(double massNumber, double atomicNumber, double density) {
		this.massNumber = massNumber;
		this.atomicNumber = atomicNumber;
		this.density = density;
	}


	public double getMassNumber() {
		return massNumber;
	}


	public double getAtomicNumber() {
		return atomicNumber;
	}


	public double getDensity() {
		return density;
	}

}

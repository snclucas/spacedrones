package org.spacedrones.materials;

public class Element {

	private double massNumber;
	private double atomicNumber;
	private double density;
	
	
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

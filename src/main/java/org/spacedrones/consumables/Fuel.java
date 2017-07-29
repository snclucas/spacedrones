package org.spacedrones.consumables;

public class Fuel {
	
	public static int LIQUID_XENON = "LiquidXenon".hashCode();
	public static int LIQUID_HYDROGEN = "LiquidHydrogen".hashCode();
	public static int LIQUID_OXYGEN = "LiquidOxygen".hashCode();
	public static int HYDRAZINE = "Hydrazine".hashCode();
	
	double density;


	public Fuel(double density) {
		this.density = density;
	}


	public double getDensity() {
		return this.density;
	}
	
}

package org.spacedrones.data;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class SpacecraftComponentData {
	
	BusComponentSpecification busComponentSpecification;
	private double[] values;

	public SpacecraftComponentData(BusComponentSpecification busComponentSpecification, double ... values) {
		super();
		this.busComponentSpecification = busComponentSpecification;
		this.values = values;
	}
	
	
	public int size() {
		return this.values.length;
	}
	
	
	public double[] getValues() {
		return this.values;
	}
	
	
	public double getValues(int index) {
		return this.values[index];
	}


	public BusComponentSpecification getBusComponentSpecification() {
		return busComponentSpecification;
	}
	
}

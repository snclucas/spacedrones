package org.spacedrones.spacecraft;

import org.spacedrones.exceptions.IncorrectUnitException;
import org.spacedrones.physics.Unit;

public class BusRequirement {
	
	private double powerRequirement;
	private double cpuThroughputRequirement;
	
	
	public BusRequirement(double powerRequirement,
			double cpuThroughputRequirement) {
		this.powerRequirement = powerRequirement;
		this.cpuThroughputRequirement = cpuThroughputRequirement;
	}


	public double getPowerRequirement(Unit unit) {
		if(unit.type() != Unit.Type.POWER)
			throw new IncorrectUnitException("Needs power unit");
		return powerRequirement / unit.value();
	}


	public double getCPUThroughputRequirement(Unit unit) {
		if(unit.type() != Unit.Type.CPU)
			throw new IncorrectUnitException("Needs CPU unit");
		return cpuThroughputRequirement / unit.value();
	}

}

package org.spacedrones.spacecraft;

public class BusRequirement {
	
	private double powerRequirement;
	private double cpuThroughputRequirement;
	
	
	public BusRequirement(double powerRequirement,
			double cpuThroughputRequirement) {
		super();
		this.powerRequirement = powerRequirement;
		this.cpuThroughputRequirement = cpuThroughputRequirement;
	}


	public double getPowerRequirement() {
		return powerRequirement;
	}


	public double getCPUThroughputRequirement() {
		return cpuThroughputRequirement;
	}

	
	

}

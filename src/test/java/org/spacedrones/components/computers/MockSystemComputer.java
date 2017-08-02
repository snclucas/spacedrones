package org.spacedrones.components.computers;

import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;


public class MockSystemComputer extends BasicSystemComputer{

	private double totalPowerAvailable = 0.0;
	private double totalCPUAvailable = 0.0;
	
	
	public MockSystemComputer(String name,
			BusComponentSpecification busResourceSpecification,
			double maxCPUThroughput, Bus spacecraftBus) {
		super(name, busResourceSpecification, maxCPUThroughput);
	}

	@Override
	public double getTotalPowerAvailable() {
		return totalPowerAvailable;
	}

	@Override
	public double getTotalCPUThroughputAvailable() {
		return totalCPUAvailable;
	}

	
	
	
	
}

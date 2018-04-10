package org.spacedrones.components.energygeneration;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractPowerGenerator extends AbstractBusComponent implements PowerGenerator {

	private double maxPower;

	public void setMaxPower(final double maxPower) {
		this.maxPower = maxPower;
	}

	AbstractPowerGenerator(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	@Override
	public double getPowerOutput(Unit unit) {
		return maxPower / unit.value();
	}

	@Override
	public void tick(double dt) {
	}
	
}

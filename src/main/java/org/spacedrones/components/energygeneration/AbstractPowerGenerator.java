package org.spacedrones.components.energygeneration;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public abstract class AbstractPowerGenerator extends AbstractBusComponent implements PowerGenerator {

	private double maxPower;

	@Override
	public TypeInfo type() {
		return new TypeInfo(this.getClass().getSimpleName());
	}

	public void setMaxPower(final double maxPower) {
		this.maxPower = maxPower;
	}

	AbstractPowerGenerator(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}

	@Override
	public final TypeInfo category() {
		return category;
	}

	@Override
	public double getMaximumPowerOutput() {
		return maxPower;
	}

	@Override
	public void tick(double dt) {
	}
	
}

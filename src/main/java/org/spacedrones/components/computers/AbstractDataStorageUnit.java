package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;


public abstract class AbstractDataStorageUnit extends AbstractBusComponent implements DataStore {
	
	AbstractDataStorageUnit(String name,
													BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}


	@Override
	public double getCurrentPower(Unit unit) {
		// Power remains constant
		return getNominalPower(unit);
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getNominalCPUThroughput(unit);
	}


	@Override
	public final TypeInfo category() {
		return categoryID;
	}


	@Override
	public void tick() {
	}
}

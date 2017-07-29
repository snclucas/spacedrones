package org.spacedrones.components.computers;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;


public abstract class AbstractDataStorageUnit extends AbstractBusComponent implements DataStore {
	
	public AbstractDataStorageUnit(String name,
			BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}


	@Override
	public double getCurrentPower() {
		// Power remains constant
		return getNominalPower();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return getNominalCPUThroughput();
	}


	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}


	@Override
	public void tick() {
	}
}

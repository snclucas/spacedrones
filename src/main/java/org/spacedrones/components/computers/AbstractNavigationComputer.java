package org.spacedrones.components.computers;

import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.structures.SubspaceBeacon;

import java.util.Map;

public abstract class AbstractNavigationComputer extends AbstractComputer implements NavigationComputer {

	Coordinates currentLocation;

	public AbstractNavigationComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
	}

	@Override
	public SystemStatusMessage updateCurrentLocation() {
		//Refresh locations of objects using sensor sweeps

		DataStore dataStore = getSystemComputer().getStorageDevice();

		// Look for subspace beacons in navigation archive
		Map<String,DataRecord> subspaceBeacons = dataStore.getData(SubspaceBeacon.class.getSimpleName());

		return null;
	}

	@Override
	public Coordinates getCurrentLocation() {






		return null;
	}


}

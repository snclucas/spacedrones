package org.spacedrones.components.computers;

import java.util.Map;

import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.structures.SubspaceBeacon;

public abstract class AbstractNavigationComputer extends AbstractComputer implements NavigationComputer {

	Coordinates currentLocation;

	public AbstractNavigationComputer(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}




	@Override
	public SystemStatusMessage updateCurrentLocation() {
		//Refresh locations of objects using sensor sweeps
		
		DataStore dataStore = getSystemComputer().getStorageDevice();
		
		// Look for subspace beacons in navigation archive
		Map<String,DataRecord> subspaceBeacons = dataStore.getData(CelestialObject.category(), SubspaceBeacon.type());
		
		
		
		
		return null;
	}


	@Override
	public Coordinates getCurrentLocation() {






		return null;
	}


}

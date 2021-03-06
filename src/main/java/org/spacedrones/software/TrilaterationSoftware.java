package org.spacedrones.software;

import org.spacedrones.components.computers.DataRecord;
import org.spacedrones.components.computers.DataStore;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;
import org.spacedrones.universe.structures.SubspaceBeacon;

import java.util.*;

public class TrilaterationSoftware extends AbstractSoftware implements Software {

	public TrilaterationSoftware(String name) {
		super(name);
	}

	@Override
	public String getDescription() {
		return "Trilateration ";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}

	private Coordinates calculatePosition() {

		DataStore dataStore = getSystemComputer().orElseThrow(() ->
            new NoSuchElementException(this.getDescription() + ": No system computer")).getStorageDevice();

		// Look for subspace beacons in navigation archive
		Map<String,DataRecord> subspaceBeacons = dataStore.getData(SubspaceBeacon.class.getSimpleName());

		//Convert to locations
		List<Location> subspaceBeaconsLocations = new ArrayList<>();
//		for (Map.Entry<String, DataRecord> entry : map.entrySet()) {
//			Location loc = new SimpleLocation(entry.getKey(), ((CelestialObject)entry.getValue()).getCoordinates()   );
//			subspaceBeaconsLocations.add(loc);
//		}

		if(subspaceBeacons.size() < 3) {
			SystemMessage message = new SystemMessage(null, this, "Not enough beacons to triangulate.");
			//getSystemComputer().addSystemMessage(message);
		}
		else {

		//	Collections.sort(subspaceBeaconsLocations, (Location loc1, Location loc2) -> loc1..compareTo(p2.firstName));



		//	DistanceSolver.solve(precision, x1, y1, z1, d1, x2, y2, z2, d2, x3, y3, z3, d3);
		}

		return null;//TODO

	}





}

package org.spacedrones.components.computers;

import org.junit.Test;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.structures.SubspaceBeacon;

import java.util.List;

public class DataRecordTest {
	
	@Test
	public void testArchiveFiltering() {
		
		DataStore dataStore = new BasicDataStorageUnit("Test store", new BusComponentSpecification());
		
		List<CelestialObject> objects = Universe.getInstance().getLocationsByType(CelestialObject.class);

		//dataStore.saveData(objects);
		
		System.out.println(dataStore.getData(CelestialObject.class.getSimpleName()).size());
		
		
		dataStore.getData(SubspaceBeacon.class.getSimpleName());
		
		System.out.println(dataStore.getData(SubspaceBeacon.class.getSimpleName()).size());
		
	}
	
}

package org.spacedrones.components.computers;

import java.util.List;

import org.junit.Test;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.structures.SubspaceBeacon;

public class DataRecordTest {
	
	@Test
	public void testArchiveFiltering() {
		
		DataStore dataStore = new BasicDataStorageUnit("Test store", new BusComponentSpecification());
		
		List<CelestialObject> objects = Universe.getInstance().getLocationsByCategory(CelestialObject.category());
		
		dataStore.saveData(objects);
		
		System.out.println(dataStore.getData(CelestialObject.category()).size());
		
		
		dataStore.getData(CelestialObject.category(), SubspaceBeacon.type());
		
		System.out.println(dataStore.getData(CelestialObject.category(), SubspaceBeacon.type()).size());
		
	}
	
}

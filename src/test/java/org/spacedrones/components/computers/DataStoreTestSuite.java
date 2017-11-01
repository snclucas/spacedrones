package org.spacedrones.components.computers;

import org.junit.Test;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.celestialobjects.StarClass;

public class DataStoreTestSuite {
	
	@Test
	public void testDataRecord() {
		
		Star sol = new Star(StarClass.G,
				SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G));
		
//		DataRecord record = new DataRecord("sol", sol);
//		assertEquals("Data record ID not correct", "sol", record.getRecordID());
//		assertEquals("Data record type not equal to its archivable data",
//				sol.category(), record.getRecordType());
//
//		assertEquals("Celestial object not found in data record", true, (record.getData() instanceof CelestialObject ) );
	}
	
}

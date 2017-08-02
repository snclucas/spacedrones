package org.spacedrones.components.computers;

import org.junit.Test;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;

import static org.junit.Assert.assertEquals;

public class DataStoreTestSuite {
	
	@Test
	public void testDataRecord() {
		
		Star sol = new Star("Sol", Star.G_CLASS_STAR,
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		
		DataRecord record = new DataRecord("sol", sol);
		assertEquals("Data record ID not correct", "sol", record.getRecordID());
		assertEquals("Data record type not equal to its archivable data", 
				sol.getCategory(), record.getRecordType());
			
		assertEquals("Celestial object not found in data record", true, (record.getData() instanceof CelestialObject ) );
	}
	
}

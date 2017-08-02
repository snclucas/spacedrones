package org.spacedrones.components.propulsion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EngineVectorTest {
	
	
	@Test
	public void testEngineVectorEquality() {
		EngineVector engineVector1 = new EngineVector(0.3,0.1, 0.5);
		EngineVector engineVector2 = new EngineVector(0.3,0.1, 0.5);
		EngineVector engineVector3 = new EngineVector(1.3,1.1, 1.5);
		
		assertEquals(engineVector1.hashCode(), engineVector2.hashCode());
		assertEquals(engineVector1, engineVector2);
		
		assertNotEquals(engineVector1.hashCode(), engineVector3.hashCode());
		assertNotEquals(engineVector1, engineVector3);
	}
	

}

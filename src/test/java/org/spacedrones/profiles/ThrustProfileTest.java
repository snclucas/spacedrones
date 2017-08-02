package org.spacedrones.profiles;

import org.junit.Test;

public class ThrustProfileTest {

	
	@Test
	public void testLinearThrusProfile() {
		
		ThrustProfile profile = ThrustProfileFactory.getThrustAlgorithm(ThrustProfileFactory.SIMPLE_LINEAR);
		
		profile.describe();
		
		profile.getCategory();
		
		profile.getName();
		
		profile.getNormalizedCPU(0);
		
		profile.getNormalizedCPU(50);
		profile.getNormalizedCPU(100);
		
		profile.getNormalizedPower(0.0);
		profile.getNormalizedPower(50.0);
		profile.getNormalizedPower(100.0);
		
		profile.getNormalizedThrust(0.0);
		profile.getNormalizedThrust(50.0);
		profile.getNormalizedThrust(100.0);
		
		profile.getType();
		
	}


	
}

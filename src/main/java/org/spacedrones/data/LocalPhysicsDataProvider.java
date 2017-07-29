package org.spacedrones.data;

public class LocalPhysicsDataProvider implements PhysicsDataProvider {
	
	

	@Override
	public double getValue(String valueTag) {
		
		if(valueTag.equals(OPTICAL_PROPAGATION_INDEX))
			return 2.0;
		if(valueTag.equals(RADAR_PROPAGATION_INDEX))
			return 2.0;
		if(valueTag.equals(GRAVIMETRIC_PROPAGATION_INDEX))
			return 2.0;
		if(valueTag.equals(MAGNETOMETRIC_PROPAGATION_INDEX))
			return 2.0;
		if(valueTag.equals(SUBSPACE_PROPAGATION_INDEX))
			return 1.01;
		
		return -1.0;
	}

}

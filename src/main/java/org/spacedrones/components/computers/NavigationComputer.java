package org.spacedrones.components.computers;

import java.math.BigDecimal;
import java.util.List;

import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;

public interface NavigationComputer extends Computer  {

	public Coordinates getCurrentLocation();
	
	public SystemStatusMessage updateCurrentLocation();
	
	public BigDecimal getDistanceToCoordinates(Coordinates location);
	
	//CelestialObject getCelestialObjectFromDatabase(int indent);
	
	void processCelestialObjects(List<CelestialObject> objects);

}

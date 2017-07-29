package org.spacedrones.navigation;

import org.spacedrones.universe.Coordinates;

public interface NavigationInterface {
	
	void getVectorToCoordinates(Coordinates coordinates);
	
	Coordinates getSpacecraftLocation();

}

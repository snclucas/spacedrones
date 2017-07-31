package org.spacedrones.universe.dataprovider;

import java.math.BigDecimal;
import java.util.Map;

import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;

public interface SpacecraftDataProvider {
	Map<String,Spacecraft> getAllSpacecraft();
	Spacecraft getSpacecraftByIdent(String ident);
	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates);
	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);	
	void updateSpacecraftLocation(String spacecraftIdent, Location location);
	Coordinates getSpacecraftLocation(String spacecraftIdent);
	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
	Map<String,Coordinates> getSpacecraftWithinRangeOfLocation(Location location, BigDecimal range);
}

package org.spacedrones.universe;

import java.math.BigDecimal;
import java.util.Map;

import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;

public interface UniverseSpacecraftLocationDataProvider {
	Map<String,Spacecraft> getSpacecraft();
	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates);
	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);	
	void updateSpacecraftLocation(String spacecraftIdent, Location location);
	Coordinates getSpacecraftLocation(String spacecraftIdent);
	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
	Map<String,Coordinates> getSpacecraftWithinRangeOfLocation(Location location, BigDecimal range);
}

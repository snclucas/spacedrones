package org.spacedrones.universe.dataprovider;

import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;

import java.math.BigDecimal;
import java.util.Map;

public interface SpacecraftDataProvider {
	Map<String,Spacecraft> getAllSpacecraft();
	Spacecraft getSpacecraftByIdent(String ident);
	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates);
	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);
	Coordinates getSpacecraftLocation(String spacecraftIdent);
	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
	Map<String,Coordinates> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range);
}

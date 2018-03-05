package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Identifiable;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ObjectLocationDataProvider {
	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity);
	void addComponent(Identifiable object, Coordinates coordinates, double[] velocity);

	List<Spacecraft> getAllSpacecraft();
	Spacecraft getSpacecraftByIdent(String ident);

  List<Identifiable> getAllObjectsByType(Class<? extends Identifiable> type);
  Identifiable getObjectByIdent(String ident);

	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);
	Coordinates getObjectLocationInUniverse(String spacecraftIdent);
	Coordinates getSpacecraftLocation(String spacecraftIdent);
	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
	Map<String,Coordinates> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range);
}

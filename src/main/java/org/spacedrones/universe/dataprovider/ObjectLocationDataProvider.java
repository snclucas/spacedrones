package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Identifiable;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;

import java.math.BigDecimal;
import java.util.*;

public interface ObjectLocationDataProvider {
	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity);
  List<Spacecraft> getAllSpacecraft();
  Optional<Spacecraft> getSpacecraftById(String ident);


	void addComponent(Identifiable object, Coordinates coordinates, double[] velocity);
  <T extends Identifiable> List<T> getAllObjectsByType(Class<T> type);
  <T> Optional<T> getObjectById(String ident, Class<? extends Identifiable> type);


	void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);
	Coordinates getObjectLocationInUniverse(String spacecraftIdent);
	Coordinates getSpacecraftLocation(String spacecraftIdent);

	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
  List<Spacecraft> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range, Unit unit);
}

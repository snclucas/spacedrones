package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.*;

import java.math.BigDecimal;
import java.util.*;

public interface ObjectLocationDataProvider {
  void populate();

  void addCelestialObject(String name, CelestialObject celestialObject, GalacticLocation location);
  Optional<CelestialObject> getCelestialObjectById(String celestialObjectID);
  Optional<Coordinates> getCelestialObjectLocationById(String celestialObjectID);

	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity);
  List<Spacecraft> getAllSpacecraft();
  Optional<Spacecraft> getSpacecraftById(String ident);
  void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);
  Coordinates getSpacecraftLocation(String spacecraftIdent);


	void addComponent(Taxonomic object, Coordinates coordinates, double[] velocity);
  <T extends Taxonomic> List<T> getAllObjectsByType(Class<T> type);
  <T> Optional<T> getObjectById(String ident, Class<? extends Taxonomic> type);




	double[] getSpacecraftVelocity(String spacecraftIdent);
	void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
	BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
  List<Spacecraft> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range, Unit unit);


  List<CelestialObject> getLocationsByType(Class<? extends CelestialObject> type);
  List<CelestialObject> getLocationsCloserThan(Coordinates coordinates, BigDecimal distance);
  List<CelestialObject> getCelestialObjectByTypeCloserThan(Class<? extends CelestialObject> type, GalacticLocation localtion, BigDecimal distance);

}

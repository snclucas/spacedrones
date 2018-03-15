package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ObjectLocationDataProvider {
  void list();
  void populate();

  void addCelestialObject(String name, CelestialObject celestialObject, Coordinates coordinates, double[] velocity);
  Optional<CelestialObject> getCelestialObjectById(String celestialObjectID);
  Optional<Coordinates> getCelestialObjectLocationById(String celestialObjectID);
  List<CelestialObject> getAllCelestialObjectsCloserThan(Coordinates coordinates, BigDecimal range, Unit unit);
  List<ObjectMeta<CelestialObject>> getAllCelestialObjectsCloserThanAsMeta(Coordinates coordinates, BigDecimal range, Unit unit);

	void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity);
  List<Spacecraft> getAllSpacecraft();
  Optional<Spacecraft> getSpacecraftById(String ident);
  void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates);
  Coordinates getSpacecraftLocation(String spacecraftIdent);
  double[] getSpacecraftVelocity(String spacecraftIdent);
  void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity);
  BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit);
  List<Spacecraft> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range, Unit unit);


  <T extends Taxonomic> List<T> getAllObjectsCloserThan(Coordinates coordinates, BigDecimal range, Unit unit);
  <T extends Taxonomic> List<T> getAllObjectsByTypeCloserThan(Class<T> type, Coordinates coordinates, BigDecimal range, Unit unit);
	void addObject(Taxonomic object, Coordinates coordinates, double[] velocity);
  <T extends Taxonomic> List<T> getAllObjectsByType(Class<T> type);

  <T> Optional<T> getObjectById(String ident);
  <T> Optional<T> getObjectByIdAndType(String ident, Class<? extends Taxonomic> type);
  Optional<Coordinates> getObjectLocationById(String ident);
}

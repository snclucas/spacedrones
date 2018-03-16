package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.exceptions.SpacecraftNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.*;
import org.spacedrones.universe.structures.*;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;


public class LocalObjectLocationDataProvider implements ObjectLocationDataProvider {

  private final Map<String, ObjectMeta> cellestialObjectsInUniverse = new HashMap<>();
	private final Map<String, ObjectMeta> objectsInUniverse = new HashMap<>();

  private static BiPredicate<Taxonomic, Class<? extends Taxonomic>> dd =
          (lhs, rhs)  -> lhs.getClass() == rhs ||
          rhs.isAssignableFrom(lhs.getClass()) ||
          Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);

  private static BiPredicate<Taxonomic, Class> dd2 =
          (lhs, rhs)  -> lhs.getClass() == rhs ||
                  rhs.isAssignableFrom(lhs.getClass()) ||
                  Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);

  public LocalObjectLocationDataProvider() {}

  @Override
  public void addCelestialObject(String name, CelestialObject celestialObject, Coordinates coordinates, double[] velocity) {
    ObjectMeta<CelestialObject> meta =
            new ObjectMeta<>(celestialObject.id(), celestialObject.name(), coordinates, velocity, celestialObject);
    cellestialObjectsInUniverse.put(celestialObject.id(), meta);
  }

  @Override
  public Optional<CelestialObject> getCelestialObjectById(String celestialObjectID) {
    Taxonomic possibleCelestialObject = cellestialObjectsInUniverse.get(celestialObjectID).object;
    if(dd.test(possibleCelestialObject, CelestialObject.class)) {
      return Optional.of((CelestialObject) possibleCelestialObject);
    }
    else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Coordinates> getCelestialObjectLocationById(String celestialObjectID) {
    ObjectMeta possibleCelestialObject = cellestialObjectsInUniverse.get(celestialObjectID);
    if(dd.test(possibleCelestialObject.object, CelestialObject.class)) {
      return Optional.of(possibleCelestialObject.coordinates);
    }
    else {
      return Optional.empty();
    }
  }

  @Override
  public List<CelestialObject> getAllCelestialObjectsCloserThan(Coordinates coordinates, BigDecimal range, Unit unit) {
    List<CelestialObject> result = new ArrayList<>();

    for (ObjectMeta meta : cellestialObjectsInUniverse.values()) {
      if (Utils.distanceToLocation(meta.coordinates, coordinates, unit).compareTo(range) <= 0) {
        result.add((CelestialObject) meta.object);
      }
    }
    return result;
  }

  @Override
  public List<ObjectMeta<CelestialObject>> getAllCelestialObjectsCloserThanAsMeta(Coordinates coordinates, BigDecimal range, Unit unit) {
    List<ObjectMeta<CelestialObject>> result = new ArrayList<>();

    for (ObjectMeta meta : cellestialObjectsInUniverse.values()) {
      if (Utils.distanceToLocation(meta.coordinates, coordinates, unit).compareTo(range) <= 0) {
        result.add(meta);
      }
    }
    return result;
  }

	@Override
	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity) {
    ObjectMeta<Spacecraft> meta =
            new ObjectMeta<>(spacecraft.id(), spacecraft.name(), coordinates, velocity, spacecraft);
		objectsInUniverse.put(spacecraft.id(), meta);
	}

	@Override
	public void addObject(Taxonomic object, Coordinates coordinates, double[] velocity) {
    ObjectMeta<Taxonomic> meta =
            new ObjectMeta<>(object.id(), object.name(), coordinates, velocity, object);
		objectsInUniverse.put(object.id(), meta);
	}

	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
    ObjectMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(!dd.test(meta.object, Spacecraft.class)) {
      return;
    }
    meta.coordinates = coordinates;
		if(objectsInUniverse.put(spacecraftIdent, meta) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}

	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
    ObjectMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(!dd.test(meta.object, Spacecraft.class)) {
      return;
    }
    meta.velocity = velocity;
    if(objectsInUniverse.put(spacecraftIdent, meta) == null)
      throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
  }

	@Override
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
    ObjectMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(meta == null || !dd.test(meta.object, Spacecraft.class))
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return meta.coordinates;
	}

	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
    ObjectMeta meta = objectsInUniverse.get(spacecraftIdent);
		if(meta == null || !dd.test(meta.object, Spacecraft.class))
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return meta.velocity;
	}

	@Override
	public BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit) {
		Coordinates coordsSpacecraft1 = getSpacecraftLocation(spacecraftIdent1);
		Coordinates coordsSpacecraft2 = getSpacecraftLocation(spacecraftIdent2);
		return Utils.distanceToLocation(coordsSpacecraft1, coordsSpacecraft2, unit);
	}

	@Override
	public List<Spacecraft> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range, Unit unit) {
		return objectsInUniverse.entrySet().stream()
            .map(Map.Entry::getValue)
				.filter(map -> Utils.distanceToLocation(coordinates, map.coordinates, unit).longValue() <= range.longValue())
            .map(m -> m.object)
            .map(Spacecraft.class::cast)
            .collect(Collectors.toList());
	}

	@Override
	public List<Spacecraft> getAllSpacecraft() {
		return objectsInUniverse.entrySet()
            .stream()
            .map(Map.Entry::getValue)
            .filter(sc -> dd.test(sc.object, Spacecraft.class))
            .map(m -> m.object)
            .map(Spacecraft.class::cast)
            .collect(Collectors.toList());
	}


	@Override
  @SuppressWarnings("unchecked operation")
  public <T extends Taxonomic> List<T> getAllObjectsByType(Class<T> type) {
    List<T> result = new ArrayList<>();

    for (ObjectMeta meta : objectsInUniverse.values()) {
      if (dd.test(meta.object, type)) {
        result.add((T) meta.object);
      }
    }
    return result;
  }

  @Override
  public <T extends Taxonomic> List<T> getAllObjectsCloserThan(Coordinates coordinates, BigDecimal range, Unit unit) {
    List<T> result = new ArrayList<>();

    for (ObjectMeta meta : objectsInUniverse.values()) {
      if (Utils.distanceToLocation(meta.coordinates, coordinates, unit).compareTo(range) <= 0) {
        result.add((T) meta.object);
      }
    }
    return result;
  }

  @Override
  public <T extends Taxonomic> List<T> getAllObjectsByTypeCloserThan(Class<T> type, Coordinates coordinates, BigDecimal range, Unit unit) {
    List<T> result = new ArrayList<>();

    for (ObjectMeta meta : objectsInUniverse.values()) {
      if (dd.test(meta.object, type) && Utils.distanceToLocation(meta.coordinates, coordinates, unit).compareTo(range) <= 0) {
        result.add((T) meta.object);
      }
    }
    return result;
  }

  public <T> Optional<T> getObjectByIdAndType(String ident, Class<? extends Taxonomic> type) {
    try {
      Taxonomic object = objectsInUniverse.get(ident).object;
      if(dd.test(object, type)) {
				return Optional.of((T)object);
			}
      else {
				return Optional.empty();
      }
    } catch (Exception e) {
      return Optional.empty();
    }

  }

  public <T> Optional<T> getObjectById(String ident) {
    try {
      Taxonomic object = objectsInUniverse.get(ident).object;
      if(object != null) {
        return Optional.of((T)object);
      }
      else {
        return Optional.empty();
      }
    } catch (Exception e) {
      return Optional.empty();
    }

  }

  public Optional<Coordinates> getObjectLocationById(String ident) {
    try {
      Coordinates object = objectsInUniverse.get(ident).coordinates;
      if(object != null) {
        return Optional.of(object);
      }
      else {
        return Optional.empty();
      }
    } catch (Exception e) {
      return Optional.empty();
    }

  }


	@Override
	public Optional<Spacecraft> getSpacecraftById(String ident) {
	  Taxonomic possibleSpacecraft = objectsInUniverse.get(ident).object;
	  if(dd.test(possibleSpacecraft, Spacecraft.class)) {
	    return Optional.of((Spacecraft) possibleSpacecraft);
    }
    else {
	    return Optional.empty();
    }
	}

  public void list() {
    objectsInUniverse.values().forEach(o->System.out.println(o.toString()));
  }

  public void populate() {

    Coordinates galacticCenterCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
    CelestialObject galacticCenter
            = new Region(new SensorSignalResponseProfile(1000.0, 1000.0, 1000.0, 1000.0, 1000.0), 10.0 * Unit.Pc.value());
    addCelestialObject("Galactic center", galacticCenter, galacticCenterCoordinates, new double[]{0.0, 0.0, 0.0});


    Coordinates solCoordinates = new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value()));
    Star sol = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.G2V.getStarClass()));

    addCelestialObject("Sol", sol, solCoordinates, new double[]{0.0, 0.0, 0.0});

    Coordinates alphaCenturiCoordinates =
            new Coordinates(
                    new BigDecimal(8*Unit.kPc.value() + 2.98*Unit.Ly.value()),
                    new BigDecimal(2.83* Unit.Ly.value()),
                    new BigDecimal(101.34*Unit.Ly.value()));

    Star alphaCenturi = new Star(StarClass.G2V,
            SensorSignalResponseLibrary.getStandardSignalResponseForStar(StarClass.O.getStarClass()));

    addCelestialObject("Alpha centuri", alphaCenturi, alphaCenturiCoordinates, new double[]{0.0, 0.0, 0.0});


    //Setup subspace beacons
    Coordinates c1 = solCoordinates.add(new Coordinates(new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1e8*Unit.Km.value())));
    GalacticLocation c1Location = new GalacticLocation("SolBeacon", c1);

    //Above Sol north pole, 1e8 Km
    addCelestialObject("SolBeacon",
            new SubspaceBeacon(
                    SensorSignalResponseLibrary
                            .getStandardSignalResponseProfileForObjectType(
                                    SensorSignalResponseLibrary.SUBSPACE_BEACON)), c1, new double[]{0.0, 0.0, 0.0});

    Coordinates c2 = alphaCenturiCoordinates.add(
            new Coordinates(
                    new BigDecimal(0.0),
                    new BigDecimal(0.0),
                    new BigDecimal(1e8*Unit.Km.value())));
    GalacticLocation c2Location = new GalacticLocation("ACBeacon", c2);

    addCelestialObject("ACBeacon",
            new SubspaceBeacon(SensorSignalResponseLibrary
                    .getStandardSignalResponseProfileForObjectType(
                            SensorSignalResponseLibrary.SUBSPACE_BEACON)), c2, new double[]{0.0, 0.0, 0.0});
  }


}

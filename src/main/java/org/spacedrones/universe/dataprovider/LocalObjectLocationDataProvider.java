package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Identifiable;
import org.spacedrones.exceptions.SpacecraftNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;


public class LocalObjectLocationDataProvider implements ObjectLocationDataProvider {

	private final Map<String,ObjectLocationMeta> objectsInUniverse = new HashMap<>();

  private static BiPredicate<Identifiable, Class<? extends Identifiable>> dd =
          (lhs, rhs)  -> lhs.getClass() == rhs ||
          rhs.isAssignableFrom(lhs.getClass()) ||
          Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);

  private static BiPredicate<Identifiable, Class> dd2 =
          (lhs, rhs)  -> lhs.getClass() == rhs ||
                  rhs.isAssignableFrom(lhs.getClass()) ||
                  Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);

  public LocalObjectLocationDataProvider() {}


	@Override
	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates, double[] velocity) {
    ObjectLocationMeta<Spacecraft> meta =
            new ObjectLocationMeta<>(spacecraft.id(), spacecraft.name(), coordinates, velocity, spacecraft);
		objectsInUniverse.put(spacecraft.id(), meta);
	}

	@Override
	public void addComponent(Identifiable object, Coordinates coordinates, double[] velocity) {
    ObjectLocationMeta<Identifiable> meta =
            new ObjectLocationMeta<>(object.id(), object.name(), coordinates, velocity, object);
		objectsInUniverse.put(object.id(), meta);
	}

	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
    ObjectLocationMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(!dd.test(meta.object, Spacecraft.class)) {
      return;
    }
    meta.coordinates = coordinates;
		if(objectsInUniverse.put(spacecraftIdent, meta) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}

	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
    ObjectLocationMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(!dd.test(meta.object, Spacecraft.class)) {
      return;
    }
    meta.velocity = velocity;
    if(objectsInUniverse.put(spacecraftIdent, meta) == null)
      throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
  }

	@Override
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
    ObjectLocationMeta meta = objectsInUniverse.get(spacecraftIdent);
    if(meta == null || !dd.test(meta.object, Spacecraft.class))
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return meta.coordinates;
	}

	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
    ObjectLocationMeta meta = objectsInUniverse.get(spacecraftIdent);
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
  public <T extends Identifiable> List<T> getAllObjectsByType(Class<T> type) {
    List<T> result = new ArrayList<>();

    for (ObjectLocationMeta meta : objectsInUniverse.values()) {
      if (dd.test(meta.object, type)) {
        result.add((T) meta.object);
      }
    }
    return result;
  }

  public <T> Optional<T> getObjectById(String ident, Class<? extends Identifiable> type) {
    try {
      Identifiable object = objectsInUniverse.get(ident).object;
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


	@Override
	public Optional<Spacecraft> getSpacecraftById(String ident) {
	  Identifiable possibleSpacecraft = objectsInUniverse.get(ident).object;
	  if(dd.test(possibleSpacecraft, Spacecraft.class)) {
	    return Optional.of((Spacecraft) possibleSpacecraft);
    }
    else {
	    return Optional.empty();
    }
	}

}

package org.spacedrones.universe.dataprovider;

import org.spacedrones.components.Identifiable;
import org.spacedrones.exceptions.SpacecraftNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LocalSpacecraftDataProvider implements SpacecraftDataProvider {
	
	private final Map<String,Identifiable> objectsInUniverse = new HashMap<>();
	private final Map<String,Coordinates> objectLocationInUniverse = new HashMap<>();
	private final Map<String,Double[]> objectVelocityInUniverse = new HashMap<>();


	public LocalSpacecraftDataProvider() {
	}


	@Override
	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates) {
		objectsInUniverse.put(spacecraft.getId(), spacecraft);
		objectLocationInUniverse.put(spacecraft.getId(), coordinates);
		objectVelocityInUniverse.put(spacecraft.getId(), new Double[]{0.0, 0.0, 0.0});
	}

	@Override
	public void addComponent(Identifiable object, Coordinates coordinates) {
		objectsInUniverse.put(object.getId(), object);
		objectLocationInUniverse.put(object.getId(), coordinates);
		objectVelocityInUniverse.put(object.getId(), new Double[]{0.0, 0.0, 0.0});
	}

	
	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		if(objectLocationInUniverse.put(spacecraftIdent, coordinates) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}
	
	
	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
		Double[] vel = new Double[]{velocity[0], velocity[1], velocity[2]};
		if(objectVelocityInUniverse.put(spacecraftIdent, vel) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}

	
	@Override
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		if(objectLocationInUniverse.get(spacecraftIdent) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return objectLocationInUniverse.get(spacecraftIdent);
	}

	@Override
	public Coordinates getObjectLocationInUniverse(final String ident) {
		return objectLocationInUniverse.get(ident);
	}
	
	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
		if(objectVelocityInUniverse.get(spacecraftIdent) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		Double[] vel = objectVelocityInUniverse.get(spacecraftIdent);
		return new double[]{vel[0], vel[1], vel[2]};
	}
	
	
	@Override
	public BigDecimal getDistanceBetweenTwoSpacecraft(String spacecraftIdent1, String spacecraftIdent2, Unit unit) {
		Coordinates coordsSpacecraft1 = getSpacecraftLocation(spacecraftIdent1);
		Coordinates coordsSpacecraft2 = getSpacecraftLocation(spacecraftIdent1);
		return Utils.distanceToLocation(coordsSpacecraft1, coordsSpacecraft2, unit);
	}


	@Override
	public Map<String,Coordinates> getSpacecraftWithinRangeOfCoordinates(Coordinates coordinates, BigDecimal range) {
		return objectLocationInUniverse.entrySet().stream()
				.filter(map -> map.getValue().equals(coordinates))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


	@Override
	public List<Spacecraft> getAllSpacecraft() {
		return objectsInUniverse.entrySet()
            .stream()
            .filter(Spacecraft.class::isInstance)
            .map(Spacecraft.class::cast)
            .collect(Collectors.toList());
	}


	@Override
	public Spacecraft getSpacecraftByIdent(String ident) {
	  Identifiable possibleSpacecraft = objectsInUniverse.get(ident);
	  if(possibleSpacecraft instanceof Spacecraft) {
	    return (Spacecraft)possibleSpacecraft;
    }
    else {
	    return null;
    }
	}

}

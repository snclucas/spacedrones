package org.spacedrones.universe.dataprovider;

import org.spacedrones.exceptions.SpacecraftNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class LocalSpacecraftDataProvider implements SpacecraftDataProvider {
	
	private final Map<String,Spacecraft> spacecraftInUniverse = new HashMap<>();
	private final Map<String,Coordinates> spacecraftLocationInUniverse = new HashMap<>();
	private final Map<String,Double[]> spacecraftVelocityInUniverse = new HashMap<>();


	public LocalSpacecraftDataProvider() {
	}


	@Override
	public void addSpacecraft(Spacecraft spacecraft, Coordinates coordinates) {
		spacecraftInUniverse.put(spacecraft.getIdent(), spacecraft);
		spacecraftLocationInUniverse.put(spacecraft.getIdent(), coordinates);
		spacecraftVelocityInUniverse.put(spacecraft.getIdent(), new Double[]{0.0, 0.0, 0.0});
	}

	
	@Override
	public void updateSpacecraftLocation(String spacecraftIdent, Coordinates coordinates) {
		if(spacecraftLocationInUniverse.put(spacecraftIdent, coordinates) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}
	
	
	@Override
	public void updateSpacecraftVelocity(String spacecraftIdent, double[] velocity) {
		Double[] vel = new Double[]{velocity[0], velocity[1], velocity[2]};
		if(spacecraftVelocityInUniverse.put(spacecraftIdent, vel) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
	}

	
	@Override
	public Coordinates getSpacecraftLocation(String spacecraftIdent) {
		if(spacecraftLocationInUniverse.get(spacecraftIdent) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		return spacecraftLocationInUniverse.get(spacecraftIdent);
	}
	
	
	@Override
	public double[] getSpacecraftVelocity(String spacecraftIdent) {
		if(spacecraftVelocityInUniverse.get(spacecraftIdent) == null)
			throw new SpacecraftNotFoundException("Spacecraft [" + spacecraftIdent + "] is not in the Universe");
		Double[] vel = spacecraftVelocityInUniverse.get(spacecraftIdent);
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
		return spacecraftLocationInUniverse.entrySet().stream()
				.filter(map -> map.getValue().equals(coordinates))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


	@Override
	public Map<String, Spacecraft> getAllSpacecraft() {
		return spacecraftInUniverse;
	}


	@Override
	public Spacecraft getSpacecraftByIdent(String ident) {
		return spacecraftInUniverse.get(ident);
	}

}

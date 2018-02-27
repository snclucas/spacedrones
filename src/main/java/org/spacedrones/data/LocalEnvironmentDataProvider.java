package org.spacedrones.data;

import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.*;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

public class LocalEnvironmentDataProvider implements EnvironmentDataProvider {
	
	public LocalEnvironmentDataProvider () {
	}

	public EnvironmentData getEnvironmentData(Coordinates coordinates) {
		final double subspaceNoise = getSubspaceNoise(coordinates);
		
		final List<CelestialObject> nearByStars =
				Universe.getInstance().getCelestialObjectByTypeCloserThan(Star.type,
								new GalacticLocation("", coordinates), new BigDecimal(Configuration.distanceForEnvironmentData));

		if(nearByStars.size() == 0)
			return new EnvironmentData(0.0, 0.0, subspaceNoise);

		double luminosity = 0.0;
		for(CelestialObject celestial : nearByStars) {
			if(celestial instanceof Star) {
				Star star = ((Star)celestial);
				BigDecimal d = Utils.distanceToLocation(coordinates, Universe.getInstance().getCelestialObjectLocationById(star.id()).getCoordinates(), Unit.One);
				SignalResponse response = star.getSensorSignalResponse().getSignalResponse(SensorType.OPTICAL, BigDecimal.ZERO);
				d = d.max(new BigDecimal(CelestialConstants.G_STAR_RADIUS));
				luminosity += response.getSignalStrength() / (4*Math.PI* (d.pow(2)).doubleValue() );
				System.out.println(star.id() + " " + response.getSignalStrength() / (4*Math.PI* (d.pow(2)).doubleValue() ));
			}
		} 
		return new EnvironmentData(luminosity, 0.0, subspaceNoise);
	}

	public double getSubspaceNoise(Coordinates coordinates) {
		return 1.0;
	}
	
}

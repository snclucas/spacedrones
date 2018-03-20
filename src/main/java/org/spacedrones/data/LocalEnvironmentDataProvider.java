package org.spacedrones.data;

import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.CelestialConstants;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.EnvironmentData;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.CelestialObject;
import org.spacedrones.universe.celestialobjects.Star;
import org.spacedrones.universe.dataprovider.LocalSignalResponseProvider;
import org.spacedrones.universe.dataprovider.SignalResponseProvider;
import org.spacedrones.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

public class LocalEnvironmentDataProvider implements EnvironmentDataProvider {

	private SignalResponseProvider signalResponseProvider = new LocalSignalResponseProvider();

	public LocalEnvironmentDataProvider () {
	}

	public EnvironmentData getEnvironmentData(Coordinates coordinates, StdAppMagnitude stdAppMagnitude) {
		final double subspaceNoise = getSubspaceNoise(coordinates);

		final List<Star> nearByStars =
				Universe.getInstance().getAllObjectsByTypeCloserThan(Star.class,
								coordinates, new BigDecimal(Configuration.distanceForEnvironmentData), Unit.Ly);

		if(nearByStars.size() == 0)
			return new EnvironmentData(0.0, 0.0, subspaceNoise);

		double luminosity = 0.0;
		for(CelestialObject celestial : nearByStars) {
			if(celestial instanceof Star) {
				Star star = ((Star)celestial);
				BigDecimal d = Utils.distanceToLocation(coordinates, Universe.getInstance().getCelestialObjectLocationById(star.id()).get(), Unit.One);
				SignalResponse response = signalResponseProvider.getEMSignalResponse(star, stdAppMagnitude, BigDecimal.ZERO);
				d = d.max(new BigDecimal(CelestialConstants.G2V_STAR_RADIUS));
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

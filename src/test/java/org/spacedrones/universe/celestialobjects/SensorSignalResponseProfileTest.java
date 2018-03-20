package org.spacedrones.universe.celestialobjects;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.data.PhysicsDataProvider;
import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.CelestialConstants;
import org.spacedrones.universe.dataprovider.SignalResponseProvider;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SensorSignalResponseProfileTest {
	
	private static PhysicsDataProvider physicsDataProvider = Configuration.getPhysicsDataProvider();
	private static SignalResponseProvider signalResponseProvider = Configuration.getSignalResponseProvider();

	//@Test
	public void testSensorSignalResponseProfileAtZeroDistance() {

		double opticalResponse = -4.2;
		double radarResponse = 5.2;
		double gravimetricResponse = 2.2;
		double magnetometricResponse = 22.2;
		double subspaceResonanceResponse = -34.3;

		SensorSignalResponseProfile sensorSignalResponseProfile = 
				new SensorSignalResponseProfile(opticalResponse, radarResponse, gravimetricResponse, magnetometricResponse, subspaceResonanceResponse);

    SignalResponse opticalResponseAtZero = signalResponseProvider.getEMSignalResponse(sensorSignalResponseProfile, StdAppMagnitude.V, new BigDecimal(0.0));
		SignalResponse radarResponseAtZero = signalResponseProvider.getEMSignalResponse(sensorSignalResponseProfile, StdAppMagnitude.K, new BigDecimal(0.0));
		SignalResponse gravimetricResponseAtZero = signalResponseProvider.getGravimetricSignalResponse(sensorSignalResponseProfile, new BigDecimal(0.0));
		SignalResponse magnetometricResponseAtZero = signalResponseProvider.getMagnetometricSignalResponse(sensorSignalResponseProfile, new BigDecimal(0.0));
		SignalResponse subspaceResonanceResponseAtZero = signalResponseProvider.getSubspaceResonanceSignalResponse(sensorSignalResponseProfile, new BigDecimal(0.0));

		assertEquals("Optical response at zero is incorrect", opticalResponse, opticalResponseAtZero.getSignalStrength(), 0.00001);
		assertEquals("RADAR response at zero is incorrect", radarResponse, radarResponseAtZero.getSignalStrength(), 0.00001);
		assertEquals("Gravimetric response at zero is incorrect", gravimetricResponse, gravimetricResponseAtZero.getSignalStrength(), 0.00001);
		assertEquals("Magnetometric response at zero is incorrect", magnetometricResponse, magnetometricResponseAtZero.getSignalStrength(), 0.00001);
		assertEquals("Subspace response at zero is incorrect", subspaceResonanceResponse, subspaceResonanceResponseAtZero.getSignalStrength(), 0.00001);
	}


	@Test
	public void testSensorSignalResponseProfileAtDistance() {
		double opticalResponse = 4.85;
		double radarResponse = 5.2;
		double gravimetricResponse = 2.2;
		double magnetometricResponse = 22.2;
		double subspaceResonanceResponse = -34.3;

		SensorSignalResponseProfile sensorSignalResponseProfile = 
				new SensorSignalResponseProfile(opticalResponse, radarResponse, gravimetricResponse, magnetometricResponse, subspaceResonanceResponse);

		double response;
		double expectedSignalAt1Ly;

		response = signalResponseProvider.getEMSignalResponse(sensorSignalResponseProfile, StdAppMagnitude.V, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("Optical luminosity at 0 distance incorrect", 1.0, CelestialConstants.G2V_STAR_LUMINOSITY /response, 0.0001);

		response = signalResponseProvider.getEMSignalResponse(sensorSignalResponseProfile, StdAppMagnitude.V, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = CelestialConstants.G2V_STAR_LUMINOSITY *(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.OPTICAL_PROPAGATION_INDEX)));
		assertEquals("Optical luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);

		response = signalResponseProvider.getEMSignalResponse(sensorSignalResponseProfile, StdAppMagnitude.V, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("RADAR luminosity at 0 distance incorrect", 1.0, 2.78618070244841E26/response, 0.0001);

	}


}

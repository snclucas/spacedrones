package org.spacedrones.universe.celestialobjects;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.components.sensors.SignalResponse;
import org.spacedrones.data.PhysicsDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.CelestialConstants;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SensorSignalResponseProfileTest {
	
	private static PhysicsDataProvider physicsDataProvider = Configuration.getPhysicsDataProvider();

	//@Test
	public void testSensorSignalResponseProfileAtZeroDistance() {

		double opticalResponse = -4.2;
		double radarResponse = 5.2;
		double gravimetricResponse = 2.2;
		double magnetometricResponse = 22.2;
		double subspaceResonanceResponse = -34.3;

		SensorSignalResponseProfile sensorSignalResponseProfile = 
				new SensorSignalResponseProfile(opticalResponse, radarResponse, gravimetricResponse, magnetometricResponse, subspaceResonanceResponse);

		SignalResponse opticalResponseAtZero = sensorSignalResponseProfile.getSignalResponse(SensorType.OPTICAL, new BigDecimal(0.0));
		SignalResponse radarResponseAtZero = sensorSignalResponseProfile.getSignalResponse(SensorType.RADAR, new BigDecimal(0.0));
		SignalResponse gravimetricResponseAtZero = sensorSignalResponseProfile.getSignalResponse(SensorType.GRAVIMETRIC, new BigDecimal(0.0));
		SignalResponse magnetometricResponseAtZero = sensorSignalResponseProfile.getSignalResponse(SensorType.MAGNETOMETRIC, new BigDecimal(0.0));
		SignalResponse subspaceResonanceResponseAtZero = sensorSignalResponseProfile.getSignalResponse(SensorType.SUBSPACE_RESONANCE, new BigDecimal(0.0));

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

		double response = 0.0;
		double expectedSignalAt1Ly = 0.0;
				
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.OPTICAL, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("Optical luminosity at 0 distance incorrect", 1.0, CelestialConstants.G_STAR_LUMINOSITY/response, 0.0001);
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.OPTICAL, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = CelestialConstants.G_STAR_LUMINOSITY*(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.OPTICAL_PROPAGATION_INDEX)));
		assertEquals("Optical luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);
		
		
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.RADAR, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("RADAR luminosity at 0 distance incorrect", 1.0, 2.78618070244841E26/response, 0.0001);
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.RADAR, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = 2.78618070244841E26*(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.RADAR_PROPAGATION_INDEX)));
		assertEquals("RADAR luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);
		
		
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.GRAVIMETRIC, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("GRAV luminosity at 0 distance incorrect", 1.0, 4.415798828277003E27/response, 0.0001);
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.GRAVIMETRIC, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = 4.415798828277003E27*(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.GRAVIMETRIC_PROPAGATION_INDEX)));
		assertEquals("GRAV luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);
		
		
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.MAGNETOMETRIC, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("MAG luminosity at 0 distance incorrect", 1.0, 4.415798828277003E19/response, 0.0001);
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.MAGNETOMETRIC, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = 4.415798828277003E19*(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.MAGNETOMETRIC_PROPAGATION_INDEX)));
		assertEquals("MAG luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);
		
		
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.SUBSPACE_RESONANCE, new BigDecimal(0.0)).getSignalStrength();
		assertEquals("SUB luminosity at 0 distance incorrect", 1.0, 1.757961177258812E42/response, 0.0001);
		response = sensorSignalResponseProfile.getSignalResponse(SensorType.SUBSPACE_RESONANCE, new BigDecimal(1 * Unit.Ly.value())).getSignalStrength();
		expectedSignalAt1Ly = 1.757961177258812E42*(1/Math.pow(Unit.Ly.value(),physicsDataProvider.getValue(PhysicsDataProvider.SUBSPACE_PROPAGATION_INDEX)));
		assertEquals("SUB luminosity at 1Ly distance incorrect", 1.0, expectedSignalAt1Ly/response, 0.0001);
	}


}

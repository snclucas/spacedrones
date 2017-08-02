package org.spacedrones.components.sensors;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.NavigationVector;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseProfile;
import org.spacedrones.universe.celestialobjects.Star;

import static org.junit.Assert.assertEquals;

public class SensorResultTest {

	@Test
	public void testSensorResult() {
		
		
		Star sol = new Star("Sol", Star.G_CLASS_STAR,  new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				new SensorSignalResponseProfile(1.0, 1.0, 1.0, 1.0, 1.0));
		
		BigDecimal distance = new BigDecimal(24423522352352345.234234);
		NavigationVector navigationVector = new NavigationVector(new BigDecimal[]{new BigDecimal(234212112312.12321),new BigDecimal(46455464.12321),new BigDecimal(97978.12321)});
	
		
		double signalStrength = 344.56;
		double signalDispersion = 674.23;
		
		SignalResponse signalResponse = new SignalResponse(signalStrength, signalDispersion);
		SensorResult sensorResult = new SensorResult(sol, distance, navigationVector, signalResponse);
		
		assertEquals("Signal responce incorrect", signalResponse, sensorResult.getSignalResponse());
		assertEquals("Navigation vector incorrect", navigationVector, sensorResult.getNavigationVector());
		assertEquals("Distance incorrect", distance.doubleValue(), sensorResult.getDistance().doubleValue(), 0.0001);
		assertEquals("Celestical object incorrect", sol, sensorResult.getCelestialObject());
		
	}
	
}

package org.spacedrones.components.sensors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SensorProfileTest {

	@Test
	public void testSensorProfile() {

		SensorType sensorType = SensorType.OPTICAL;
		double signalGain = 345.23;
		double signalThreshod = 34232.23;
		
		SensorProfile sensorProfile = new SensorProfile(sensorType, signalThreshod, signalGain);
		assertEquals("Sensor type incorrect", SensorType.OPTICAL, sensorProfile.getSensorType());
		assertEquals("Sensor threshold incorrect", signalThreshod, sensorProfile.getSignalThreshold(), 0.0001);
		assertEquals("Sensor gain incorrect", signalGain, sensorProfile.getSignalGain(), 0.0001);

		double newSignalGain = 11345.23;
		double newSignalThreshod = 1134232.23;
		sensorProfile = new SensorProfile(SensorType.GRAVIMETRIC, newSignalThreshod, newSignalGain);
		
		assertEquals("Sensor type incorrect", SensorType.GRAVIMETRIC, sensorProfile.getSensorType());
		assertEquals("Sensor threshold incorrect", newSignalThreshod, sensorProfile.getSignalThreshold(), 0.0001);
		assertEquals("Sensor gain incorrect", newSignalGain, sensorProfile.getSignalGain(), 0.0001);
		
		
	}

}

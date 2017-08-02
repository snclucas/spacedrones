package org.spacedrones.components.sensors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.components.TypeInfo;

public class SensorProfileTest {

	@Test
	public void testSensorProfile() {
		
		TypeInfo sensorType = Sensor.OPTICAL;
		double signalGain = 345.23;
		double signalThreshod = 34232.23;
		
		SensorProfile sensorProfile = new SensorProfile(sensorType, signalThreshod, signalGain);
		assertEquals("Sensor type incorrect", Sensor.OPTICAL, sensorProfile.getSensorType());
		assertEquals("Sensor threshold incorrect", signalThreshod, sensorProfile.getSignalThreshold(), 0.0001);
		assertEquals("Sensor gain incorrect", signalGain, sensorProfile.getSignalGain(), 0.0001);
		
		
		double newSignalGain = 11345.23;
		double newSignalThreshod = 1134232.23;
		
		sensorProfile.setSensorType(Sensor.GRAVIMETRIC);
		sensorProfile.setSignalGain(newSignalGain);
		sensorProfile.setSignalThreshold(newSignalThreshod);
		
		
		assertEquals("Sensor type incorrect", Sensor.GRAVIMETRIC, sensorProfile.getSensorType());
		assertEquals("Sensor threshold incorrect", newSignalThreshod, sensorProfile.getSignalThreshold(), 0.0001);
		assertEquals("Sensor gain incorrect", newSignalGain, sensorProfile.getSignalGain(), 0.0001);
		
		
	}

}

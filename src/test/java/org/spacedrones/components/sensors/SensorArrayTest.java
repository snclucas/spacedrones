package org.spacedrones.components.sensors;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.StdAppMagnitude;
import org.spacedrones.physics.Unit;

import static org.junit.Assert.assertEquals;

public class SensorArrayTest {
	
	private SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

	@Test
	public void testLinearSensorArray() {
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(LinearSensorArray.class.getSimpleName());
		
		double gainPerElement = 10.0;
		double signalDetectionThreshold = 9;
		int numberOfSensorElements = 23;
		StdAppMagnitude stdAppMagnitudes = StdAppMagnitude.V;
		
		EMSensorProfile sensorProfile = new EMSensorProfile(stdAppMagnitudes, EMSensorThreshold.asMagnitude(signalDetectionThreshold, stdAppMagnitudes), gainPerElement);
		
		Sensor linearSensor = new LinearSensorArray(
				LinearSensorArray.class.getSimpleName(), data.getBusComponentSpecification(), sensorProfile, numberOfSensorElements);

		assertEquals("Sensor gain incorrect (linear array)", gainPerElement*numberOfSensorElements, linearSensor.getSensorGain(), 0.0001);
		assertEquals("Sensor detection threshold incorrect (linear array)", signalDetectionThreshold, linearSensor.getSensorThreshold().getThresholdInWattsPerMeter(), 0.0001);

		assertEquals("Sensor mass incorrect (linear array)", data.getBusComponentSpecification().getMass(Unit.kg)*numberOfSensorElements, linearSensor.getMass(Unit.kg), 0.0001);
		assertEquals("Sensor volume incorrect (linear array)", data.getBusComponentSpecification().getVolume(Unit.m3)*numberOfSensorElements, linearSensor.getVolume(Unit.m3), 0.0001);
	}
	
	
	@Test
	public void testFractalSensorArray() {
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(FractalSensorArray.class.getSimpleName());
		
		double gainPerElement = 100.0;
		double signalDetectionThreshold = -9;	
		int numberOfSensorElements = 23;

    Sensor fractalSensor = SensorFactory.getSensor(FractalSensorArray.class.getSimpleName(), SensorType.OPTICAL, numberOfSensorElements);

		assertEquals("Sensor gain incorrect (fractal array)", gainPerElement*numberOfSensorElements, fractalSensor.getSensorGain(), 0.0001);
		assertEquals("Sensor detection threshold incorrect (fractal array)", signalDetectionThreshold, fractalSensor.getSensorThreshold().getThresholdInWattsPerMeter(), 0.0001);

		assertEquals("Sensor mass incorrect (fractal array)", data.getBusComponentSpecification().getMass(Unit.kg)*numberOfSensorElements, fractalSensor.getMass(Unit.kg), 0.0001);
		assertEquals("Sensor volume incorrect (fractal array)", data.getBusComponentSpecification().getVolume(Unit.m3)*numberOfSensorElements, fractalSensor.getVolume(Unit.m3), 0.0001);
	}

}

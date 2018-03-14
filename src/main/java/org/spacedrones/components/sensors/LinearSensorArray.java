package org.spacedrones.components.sensors;

import org.spacedrones.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {

	LinearSensorArray(String name,
										BusComponentSpecification busResourceSpecification,
										SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}

}

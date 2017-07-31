package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
	
	
	LinearSensorArray(String name,
										BusComponentSpecification busResourceSpecification,
										SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}
	
	
	@Override
	public String describe() {
		return "Linear sensor array.";
	}

	
	public static TypeInfo type() {
		return new TypeInfo("LinearSensorArray");
	}

	
	@Override
	public final TypeInfo getType() {
		return type();
	}


	@Override
	public TypeInfo getCategory() {
		return Sensor.category;
	}
	

}

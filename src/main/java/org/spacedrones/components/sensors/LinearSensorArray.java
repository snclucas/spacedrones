package org.spacedrones.components.sensors;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class LinearSensorArray extends BasicSensorArray {
  public static TypeInfo type = new TypeInfo("LinearSensorArray");

	LinearSensorArray(String name,
										BusComponentSpecification busResourceSpecification,
										SensorProfile sensorProfile, int numberOfSensorElements) {
		super(name, busResourceSpecification, sensorProfile,
				numberOfSensorElements);
	}

	@Override
	public final TypeInfo getType() {
		return new TypeInfo("LinearSensorArray");
	}

  @Override
  public String describe() {
    return "LinearSensorArray";
  }
}

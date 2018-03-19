package org.spacedrones.components.sensors;

import org.spacedrones.spacecraft.*;

import java.util.*;

public class StarTracker extends AbstractSensor {

	public StarTracker(String name,
              BusComponentSpecification busResourceSpecification,
              SensorProfile sensorProfile) {
		super(name, busResourceSpecification, sensorProfile);
	}

  public List<SensorResult> activeScan(double duration, double signalStrength, SignalPropagationModel propagationModel, int sensorType) {
	  return Collections.EMPTY_LIST;
  }

}

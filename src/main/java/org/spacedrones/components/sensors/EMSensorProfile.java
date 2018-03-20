package org.spacedrones.components.sensors;

import org.spacedrones.physics.StdAppMagnitude;

public class EMSensorProfile {

	private final StdAppMagnitude stdAppMagnitude;
	private final EMSensorThreshold signalThreshold;
	private final double signalGain;
	
	
	public EMSensorProfile(final StdAppMagnitude stdAppMagnitude, EMSensorThreshold signalThreshold, double signalGain) {
		this.stdAppMagnitude = stdAppMagnitude;
		this.signalThreshold = signalThreshold;
		this.signalGain = signalGain;
	}

  public StdAppMagnitude getStdAppMagnitude() {
    return stdAppMagnitude;
  }

  EMSensorThreshold getSignalThreshold() {
		return signalThreshold;
	}

	double getSignalGain() {
		return signalGain;
	}

}
package org.spacedrones.components.sensors;

import java.math.BigDecimal;

public class PowerLawSignalPropagationModel implements SignalPropagationModel {

	private double exponent;

	public PowerLawSignalPropagationModel(double exponent) {
		this.exponent = exponent;
	}


	@Override
	public double getSignal(double logInitalPower, BigDecimal distance) {
		if(distance.compareTo(new BigDecimal(1e-50)) < 0)
			return logInitalPower;
		double initialPower = Math.pow(10, logInitalPower);
		double signal = initialPower * 1.0/((Math.pow(distance.doubleValue(), exponent)));
		return Math.log10(signal);
	}

}

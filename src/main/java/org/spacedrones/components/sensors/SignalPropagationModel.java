package org.spacedrones.components.sensors;

import java.math.BigDecimal;

public interface SignalPropagationModel {

	double getSignal(double initalPower, BigDecimal distance);

}

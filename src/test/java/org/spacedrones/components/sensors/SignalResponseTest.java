package org.spacedrones.components.sensors;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SignalResponseTest {

	@Test
	public void testSignalResponse() {
		
		double signalStrength = 344.56;
		double signalDispersion = 674.23;
		
		SignalResponse signalResponse = new SignalResponse(signalStrength, signalDispersion);
		assertEquals("Signal strength incorrect", signalStrength, signalResponse.getSignalStrength(), 0.0001);
		assertEquals("Signal dispersion incorrect", signalDispersion, signalResponse.getSignalDispersion(), 0.0001);
	}
	
	
	@Test
	public void testSignalResponseEquality() {
		
		double signalStrength = 344.56;
		double signalDispersion = 674.23;
		
		SignalResponse signalResponse = new SignalResponse(signalStrength, signalDispersion);
		SignalResponse signalResponse2 = new SignalResponse(signalStrength, signalDispersion);
		
		assertEquals("Signal response equality incorrect", signalResponse, signalResponse2);
		assertEquals("Signal response hashcode incorrect", signalResponse.hashCode(), signalResponse2.hashCode());
	}

}

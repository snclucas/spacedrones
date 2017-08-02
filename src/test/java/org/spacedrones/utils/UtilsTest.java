package org.spacedrones.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;

public class UtilsTest {

	@Test
	public void testCoordinateConversion() {

		//	17h 42.4m
		//−28.92°

		Coordinates coords = Utils.galacticCoordinatesToAbsoluteCoordinates(270, 10, new BigDecimal(1*Unit.kPc.value()));
		System.out.println(coords.get(0, Unit.Pc.value()).doubleValue() + " " + 
				coords.get(1, Unit.Pc.value()).doubleValue() + " " + coords.get(2, Unit.Ly.value()).doubleValue());



	}


	@Test
	public void testHMS2Deg() {

		//System.out.println(Utils.hmsToDeg(1,1,30));
		//System.out.println(Utils.RAToDeg(17,42,4));
	}


}

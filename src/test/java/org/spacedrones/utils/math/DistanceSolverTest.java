package org.spacedrones.utils.math;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceSolverTest {

	@Test
	public void testDistanceSolver() {
		double[] pos = new double[3];

		pos = DistanceSolver.solve(1e-12, 
				0.0, 0.0, 0.0, Math.sqrt(50), 
				10.0, 0.0, 0.0, Math.sqrt(50), 
				5.0, 10.0, 0.0, 5.0);

		assertEquals("X postion incorrect (1)", 5.0, pos[0], 0.0001);
		assertEquals("Y postion incorrect (1)", 5.0, pos[1], 0.0001);
		assertEquals("Z postion incorrect (1)", 0.0, pos[2], 0.0001);

		pos = DistanceSolver.solve(1e-12, 
				0.0, 0.0, 10.0, Math.sqrt(200), 
				10.0, 0.0, 10.0, 10.0, 
				0.0, 10.0, 10.0, 10.0);

		assertEquals("X postion incorrect (2)", 10.0, pos[0], 0.0001);
		assertEquals("Y postion incorrect (2)", 10.0, pos[1], 0.0001);
		assertEquals("Z postion incorrect (2)", 10.0, pos[2], 0.0001);
	}

	@Test
	public void testDistanceSolverBigDecimal() {
		BigDecimal[] pos = new BigDecimal[3];

		pos = DistanceSolver.solve(new BigDecimal(1e-12), 
				new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(Math.sqrt(50)), 
				new BigDecimal(10.0), new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(Math.sqrt(50)), 
				new BigDecimal(5.0), new BigDecimal(10.0), new BigDecimal(0.0), new BigDecimal(5.0));

		assertEquals("X postion incorrect (1)", 5.0, pos[0].doubleValue(), 0.0001);
		assertEquals("Y postion incorrect (1)", 5.0, pos[1].doubleValue(), 0.0001);
		assertEquals("Z postion incorrect (1)", 0.0, pos[2].doubleValue(), 0.0001);

	}


}

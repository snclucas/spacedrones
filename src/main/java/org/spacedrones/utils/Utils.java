package org.spacedrones.utils;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.spacedrones.Configuration;
import org.spacedrones.physics.Unit;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.NavigationVector;
import org.spacedrones.utils.math.BigDecimalMath;
import org.spacedrones.utils.math.MathUtils;

public class Utils {

	public static BigDecimal[] doubleArrayToBigDecimalArray(double[] doubleArray) {
		BigDecimal[] bigDecimalArray = new BigDecimal[doubleArray.length];
		for(int i = 0;i<doubleArray.length;i++)
			bigDecimalArray[i] = new BigDecimal(doubleArray[i], Configuration.mc);
		return bigDecimalArray;
	}
	
	
	
	public static BigDecimal distanceToLocation(Coordinates coordinates1, Coordinates coordinates2, Unit unit)
	{
		BigDecimal dx = (coordinates1.get(0).subtract(coordinates2.get(0))).divide(new BigDecimal(unit.value()), Configuration.mc);
		BigDecimal dy = (coordinates1.get(1).subtract(coordinates2.get(1))).divide(new BigDecimal(unit.value()), Configuration.mc);
		BigDecimal dz = (coordinates1.get(2).subtract(coordinates2.get(2))).divide(new BigDecimal(unit.value()), Configuration.mc);
		return MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
	}


	public static NavigationVector vectorToLocation(Coordinates coordinates1, Coordinates coordinates2, boolean normalized) {
		BigDecimal dx = coordinates2.get(0).subtract(coordinates1.get(0));
		BigDecimal dy = coordinates2.get(1).subtract(coordinates1.get(1));
		BigDecimal dz = coordinates2.get(2).subtract(coordinates1.get(2));
		
		BigDecimal length = MathUtils.bigSqrt(dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)));
		
		System.out.println(length);
		
		if(normalized)
			return new NavigationVector(
					dx.divide(length, Configuration.mc), 
					dy.divide(length, Configuration.mc), 
					dz.divide(length, Configuration.mc));
		else 
			return new NavigationVector(dx, dy, dz);
	}
	
	
	
	public static Coordinates galacticCoordinatesToAbsoluteCoordinates(double rightAscension, double declination, BigDecimal distance) {
		BigDecimal xSol = new BigDecimal(0.0, Configuration.mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal ySol = new BigDecimal(-8 * Unit.kPc.value(), Configuration.mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal zSol = new BigDecimal(100 * Unit.Ly.value(), Configuration.mc).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimal RA = new BigDecimal(rightAscension).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		BigDecimal dec = new BigDecimal(declination).setScale(Configuration.precision, Configuration.ROUNDING_MODE);
		
		BigDecimalMath.toRadians(RA);
		
		BigDecimal x = distance.multiply(BigDecimalMath.sin(BigDecimalMath.toRadians(RA))).add(xSol, Configuration.mc);
		BigDecimal y = distance.multiply(BigDecimalMath.cos(BigDecimalMath.toRadians(RA))).add(ySol, Configuration.mc);	
		BigDecimal z = distance.multiply(BigDecimalMath.sin(BigDecimalMath.toRadians(dec))).add(zSol, Configuration.mc);
		return new Coordinates(x, y, z);
		
	}
	
	
	public static double hmsToDeg(double h, double m, double s) {
		int multplier = (h>=0)?1:-1;
		return multplier*(Math.abs(h) + (m/60.0) + (s/3600.0));
	}
	
	public static double RAToDeg(double h, double m, double s) {
		int multplier = (h>=0)?1:-1;
		return 15*multplier*(Math.abs(h) + (m/60.0) + (s/3600.0));
	}
	
	
	public static double[] mergeArray(double[] array1, double[] array2) {
		if(array1.length != array2.length)
			throw new InvalidParameterException();
		double[] mergedArray = new double[array1.length];
		for(int i = 0;i< array1.length;i++)
			mergedArray[i] = array1[i] + array2[i];
		return mergedArray;
	}
	
	

}

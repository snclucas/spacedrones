package org.spacedrones.profiles;

import java.math.BigDecimal;

import org.spacedrones.utils.math.BigDecimalMath;

public class PowerLawProfile <T extends Number> {

	private T a;
	private T power;
	
	private Class<T> clas;
	
	
	//public PowerLawProfile(double a, double power) {
	//	super();
	//	this.a = new BigDecimal(a);
	//	this.power = new BigDecimal(power);
	//}
	
	public PowerLawProfile(T a, T power, Class<T> clas) {
		super();
		this.a = a;
		this.power = power;
		this.clas = clas;
	}


	public T getA() {
		return a;
	}

	public void setA(T a) {
		this.a = a;
	}

	public T getPower() {
		return power;
	}

	public void setPower(T power) {
		this.power = power;
	}

	//@Override
	public T getValue(T input) {

		
		if(input instanceof BigDecimal)
			return (T) BigDecimalMath.pow((BigDecimal)input, (BigDecimal)power).multiply((BigDecimal)a);
		else
			return (T) (Double) ((Double)(Math.pow((Double)input, (Double)power))*((Double)a))  ;

	}
	
//	@Override
//	public double getValue(double input) {
//		Double f = 6.0;
//		return a.doubleValue()*Math.pow(input, power.doubleValue());
//	}
	
}

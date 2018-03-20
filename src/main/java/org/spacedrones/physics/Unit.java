package org.spacedrones.physics;

public class Unit {
	
	public enum Type {ANGLE, DIMENTIONLESS, LENGTH, MASS, AREA, VOLUME, TIME, FORCE, POWER, CPU, ENERGY, TEMPERATURE, FLUX_DENSITY, VELOCITY}


  public static Unit Jy = new Unit(1.0e-26, Type.FLUX_DENSITY, "Jy");

	//Unit of temperature
	public static Unit K = new Unit(1.0, Type.TEMPERATURE, "K");
	public static Unit degC = new Unit(1.0, Type.TEMPERATURE, "degC");

	//Units for mass
	public static Unit g = new Unit(1.0e-3, Type.MASS, "g");
	public static Unit kg = new Unit(1.0, Type.MASS, "kg");
	public static Unit Tonne = new Unit(1.0e3 * Unit.kg.value(), Type.MASS, "Tonne");
	
	public static Unit One = new Unit(1.0, Type.DIMENTIONLESS, "");

	//Units for area
	public static Unit m2 = new Unit(1.0, Type.AREA, "m2");
	
	//Units for volume
	public static Unit m3 = new Unit(1.0, Type.VOLUME, "m3");
	public static Unit l = new Unit(1.0e-3, Type.VOLUME, "l");
	
	//Units for thrust/force
	public static Unit N = new Unit(1.0, Type.FORCE, "N");
	public static Unit kN = new Unit(1.0e3 * Unit.N.value(), Type.FORCE, "kN");
	public static Unit MN = new Unit(1.0e3 * Unit.kN.value(), Type.FORCE, "MN");
	
	
	//Units for CPU throughput
	public static Unit kFLOPs = new Unit(1.0e-3, Type.CPU, "kFLOPs");
	public static Unit MFLOPs = new Unit(1.0, Type.CPU, "MFLOPs");
	public static Unit GFLOPs = new Unit(1.0e3, Type.CPU, "GFLOPs");
	public static Unit TFLOPs = new Unit(1.0e6, Type.CPU, "TFLOPs");
	
	
	//Units for power
	public static Unit uW = new Unit(1.0e-6, Type.POWER, "uW");
	public static Unit mW = new Unit(1.0e-3, Type.POWER, "mW");
	public static Unit W = new Unit(1.0, Type.POWER, "W");
	public static Unit kW = new Unit(1.0e3, Type.POWER, "kW");
	public static Unit MW = new Unit(1.0e6, Type.POWER, "MW");
	public static Unit GW = new Unit(1.0e9, Type.POWER, "GW");
	
	//Units for speed
	//public static double mps = Unit.m3 / Unit.s;
	//public static double lmps = 1e3 * mps;
	//public static double c = Constants.c;
	
	
	//Units for energy
	public static Unit GJ = new Unit(1.0e9, Type.ENERGY, "GJ");
	
	//Units for length
  public static Unit nm = new Unit(1.0e-9, Type.LENGTH, "um");
  public static Unit um = new Unit(1.0e-6, Type.LENGTH, "um");
	public static Unit mm = new Unit(1.0e-3, Type.LENGTH, "mm");
	public static Unit cm = new Unit(1.0e-2, Type.LENGTH, "cm");
	public static Unit m = new Unit(1.0, Type.LENGTH, "m");
	public static Unit Km = new Unit(1.0e3, Type.LENGTH, "Km");
	public static Unit Ly = new Unit(9460730472580800.0, Type.LENGTH, "Ly");
	public static Unit kLy = new Unit(1.0e3 * Unit.Ly.value(), Type.LENGTH, "kLy");
	public static Unit Pc = new Unit(3.2615679661840633266036314297735 * Unit.Ly.value(), Type.LENGTH, "Pc");
	public static Unit kPc = new Unit(1.0e3 * Unit.Pc.value(), Type.LENGTH, "kPc");
	public static Unit AU = new Unit(1.49597870700e11 * Unit.m.value(), Type.LENGTH, "AU");
	
	//Units for time
	public static Unit s = new Unit(1.0, Type.TIME, "s");
  public static Unit min = new Unit(60.0, Type.TIME, "s");
  public static Unit hour = new Unit(3600.0, Type.TIME, "s");
	public static Unit day = new Unit(86400.0, Type.TIME, "day");
	public static Unit year = new Unit(365 * 86400.0, Type.TIME, "year");
	
	public static Unit percent = new Unit(1.0e-2, Type.DIMENTIONLESS, "%");

	public static Unit degrees = new Unit(Math.PI / 180.0, Type.ANGLE, "deg");
	public static Unit radians = new Unit(1.0, Type.ANGLE, "rad");


  //Unit of velocity
  public static Unit Kps = new Unit(1.0 * Unit.Km.value() / Unit.s.value(), Type.VELOCITY, "Km/s");
  public static Unit Kph = new Unit(1.0 * Unit.Km.value() / Unit.hour.value(), Type.VELOCITY, "Km/h");
	
	private double value;
	private Type type;
	private String symbol;
	
	public Unit(double value, Type type, String symbol) {
		this.value = value;
		this.type = type;
		this.symbol = symbol;
	}
	
	public double value() {
		return this.value;
	}
	
	public Type type() {
		return this.type;
	}
	
	public String symbol() {
		return this.symbol;
	}

}

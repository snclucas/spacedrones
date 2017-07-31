package org.spacedrones.spacecraft;

import org.spacedrones.exceptions.IncorrectUnitException;
import org.spacedrones.physics.Unit;

public class PhysicalSpecification {
	
	private double mass;
	private double volume;
	
	private double length;
	private double width;
	private double height;
	
	
	public PhysicalSpecification(double mass, double volume, double length,
			double width, double height) {
		super();
		this.mass = mass;
		this.volume = volume;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	
	public PhysicalSpecification(double mass, double volume) {
		super();
		this.mass = mass;
		this.volume = volume;
		this.length = -1;
		this.width = -1;
		this.height = -1;
	}


	public PhysicalSpecification() {
		super();
		this.mass = 0.0;
		this.volume = 0.0;
		this.length = 0.0;
		this.width = 0.0;
		this.height = 0.0;
	}


	public double getMass() {
		return getMass(Unit.kg);
	}
	
	
	public double getMass(Unit unit) {
		if(unit.type() != Unit.Type.MASS)
			throw new IncorrectUnitException("Expecting volume unit");
		return mass / unit.value();
	}


	public void setMass(double mass) {
		this.mass = mass;
	}


	public double getVolume() {
		return volume;
	}
	
	
	public double getVolume(Unit unit) {
		if(unit.type() != Unit.Type.VOLUME)
			throw new IncorrectUnitException("Expecting volume unit");
		return volume / unit.value();
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public double getLength() {
		return length;
	}


	public double getLength(Unit unit) {
		return length / unit.value();
	}


	public void setLength(double length) {
		this.length = length;
	}


	public double getWidth() {
		return width;
	}


	public double getWidth(Unit unit) {
		return width / unit.value();
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public double getHeight(Unit unit) {
		return height / unit.value();
	}


	public void setHeight(double height) {
		this.height = height;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhysicalSpecification other = (PhysicalSpecification) obj;
		if (Double.doubleToLongBits(height) != Double
				.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(length) != Double
				.doubleToLongBits(other.length))
			return false;
		if (Double.doubleToLongBits(mass) != Double
				.doubleToLongBits(other.mass))
			return false;
		if (Double.doubleToLongBits(volume) != Double
				.doubleToLongBits(other.volume))
			return false;
    return Double.doubleToLongBits(width) == Double
            .doubleToLongBits(other.width);
  }
	
	
	
	
}

package org.spacedrones.spacecraft;

import org.spacedrones.physics.Unit;

public class OperationalSpecification {

	private double nominalPower;
	private double nominalCPUThroughout;
	
	private double maximumOperationalPower;
	private double maximumOperationalCPUThroughput;
	
	
	public OperationalSpecification(double nominalPower,
			double nominalCPUThroughout, double maximumOperationalPower,
			double maximumOperationalCPUThroughput) {
		super();
		this.nominalPower = nominalPower;
		this.nominalCPUThroughout = nominalCPUThroughout;
		this.maximumOperationalPower = maximumOperationalPower;
		this.maximumOperationalCPUThroughput = maximumOperationalCPUThroughput;
	}
	
	
	
	public OperationalSpecification(double nominalPower,
			double nominalCPUThroughout) {
		super();
		this.nominalPower = nominalPower;
		this.nominalCPUThroughout = nominalCPUThroughout;
		this.maximumOperationalPower = nominalPower;
		this.maximumOperationalCPUThroughput = nominalCPUThroughout;
	}


	public OperationalSpecification() {
		super();
		this.nominalPower = 0.0;
		this.nominalCPUThroughout = 0.0;
		this.maximumOperationalPower = 0.0;
		this.maximumOperationalCPUThroughput = 0.0;
	}

	public double getNominalPower(Unit unit) {
		return nominalPower / unit.value();
	}



	public void setNominalPower(double nominalPower) {
		this.nominalPower = nominalPower;
	}


	public double getNominalCPUThroughout(Unit unit) {
		return nominalCPUThroughout / unit.value();
	}



	public void setNominalCPUThroughout(double nominalCPUThroughout) {
		this.nominalCPUThroughout = nominalCPUThroughout;
	}



	public double getMaximumOperationalPower(Unit unit) {
		return maximumOperationalPower / unit.value();
	}



	public void setMaximumOperationalPower(double maximumOperationalPower) {
		this.maximumOperationalPower = maximumOperationalPower;
	}



	public double getMaximumOperationalCPUThroughput(Unit unit) {
		return maximumOperationalCPUThroughput / unit.value();
	}



	public void setMaximumOperationalCPUThroughput(
			double maximumOperationalCPUThroughput) {
		this.maximumOperationalCPUThroughput = maximumOperationalCPUThroughput;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maximumOperationalCPUThroughput);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maximumOperationalPower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(nominalCPUThroughout);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(nominalPower);
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
		OperationalSpecification other = (OperationalSpecification) obj;
		if (Double.doubleToLongBits(maximumOperationalCPUThroughput) != Double
				.doubleToLongBits(other.maximumOperationalCPUThroughput))
			return false;
		if (Double.doubleToLongBits(maximumOperationalPower) != Double
				.doubleToLongBits(other.maximumOperationalPower))
			return false;
		if (Double.doubleToLongBits(nominalCPUThroughout) != Double
				.doubleToLongBits(other.nominalCPUThroughout))
			return false;
		if (Double.doubleToLongBits(nominalPower) != Double
				.doubleToLongBits(other.nominalPower))
			return false;
		return true;
	}
	
	

}

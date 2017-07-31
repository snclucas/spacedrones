package org.spacedrones.spacecraft;

import org.spacedrones.physics.Unit;

public class BusComponentSpecification {
	
	private PhysicalSpecification physicalSpecification;
	private OperationalSpecification operationalSpecification;
	
	
	public BusComponentSpecification(PhysicalSpecification physicalSpecification, OperationalSpecification operationalSpecification) {
		super();
		this.physicalSpecification = physicalSpecification;
		this.operationalSpecification = operationalSpecification;
	}
	
	
	public BusComponentSpecification() {
		super();
		this.physicalSpecification = new PhysicalSpecification();
		this.operationalSpecification = new OperationalSpecification();
	}

	

	public double getLength(Unit unit) {
		return physicalSpecification.getLength(unit);
	}
	
	
	public void setLength(double length) {
		physicalSpecification.setLength(length);
	}
	
	
	public double getWidth(Unit unit) {
		return physicalSpecification.getWidth(unit);
	}
	
	
	public void setWidth(double width) {
		physicalSpecification.setWidth(width);
	}


	public double getHeight(Unit unit) {
		return physicalSpecification.getHeight(unit);
	}
	
	
	public void setHeight(double height) {
		physicalSpecification.setHeight(height);
	}

	
	public double getMass(Unit unit) {
		return physicalSpecification.getMass(unit);
	}
	
	
	public void setMass(double mass) {
		physicalSpecification.setMass(mass);
	}


	public double getVolume(Unit unit) {
		return physicalSpecification.getVolume(unit);
	}
	
	
	public void setVolume(double volume) {
		physicalSpecification.setVolume(volume);
	}

	
	public double getNominalPower(Unit unit) {
		return operationalSpecification.getNominalPower(unit) / unit.value();
	}
	
	
	public void setNominalPower(double nominalPower) {
		operationalSpecification.setNominalPower(nominalPower);
	}


	public double getNominalCPUThroughout(Unit unit) {
		return operationalSpecification.getNominalCPUThroughout(unit);
	}
	
	public void setNominalCPUThroughout(double nominalCPUThroughout) {
		operationalSpecification.setNominalCPUThroughout(nominalCPUThroughout);
	}

	
	public double getMaximumOperationalPower(Unit unit) {
		return operationalSpecification.getMaximumOperationalPower(unit) / unit.value();
	}
	
	
	public void setMaximumOperationalPower(double maximumOperationalPower) {
		operationalSpecification.setMaximumOperationalPower(maximumOperationalPower);
	}


	public double getMaximumOperationalCPUThroughput(Unit unit) {
		return operationalSpecification.getMaximumOperationalCPUThroughput(unit);
	}
	
	
	public void setMaximumOperationalCPUThroughput(double maximumOperationalCPUThroughput) {
		operationalSpecification.setMaximumOperationalCPUThroughput(maximumOperationalCPUThroughput);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((operationalSpecification == null) ? 0
						: operationalSpecification.hashCode());
		result = prime
				* result
				+ ((physicalSpecification == null) ? 0 : physicalSpecification
						.hashCode());
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
		BusComponentSpecification other = (BusComponentSpecification) obj;
		if (operationalSpecification == null) {
			if (other.operationalSpecification != null)
				return false;
		} else if (!operationalSpecification
				.equals(other.operationalSpecification))
			return false;
		if (physicalSpecification == null) {
			if (other.physicalSpecification != null)
				return false;
		} else if (!physicalSpecification.equals(other.physicalSpecification))
			return false;
		return true;
	}


	
}

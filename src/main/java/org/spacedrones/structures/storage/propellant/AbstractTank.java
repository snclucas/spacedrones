package org.spacedrones.structures.storage.propellant;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.exceptions.EmptyTankException;
import org.spacedrones.materials.*;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractTank extends AbstractBusComponent implements Tank {
	private double volumeOfContents;
	private final double capacity;

	protected Fluid fluid;

	AbstractTank(String name, BusComponentSpecification busResourceSpecification, double capacity) {
		super(name, busResourceSpecification);
		this.fluid = null;
		this.capacity = capacity;
		this.volumeOfContents = 0.0;
	}

	@Override
	public double getMass(Unit unit) {
		if(fluid != null)
			return super.getMass(unit) + fluid.getDensity() * volumeOfContents;
		else
			return super.getMass(unit);
	}

  @Override
	public Fluid getContents() {
		if(fluid != null)
			return fluid;
		else
			throw new EmptyTankException("No fluid in tank [" + name() + "]");
	}

  @Override
	public void setContents(Fluid fluid) {
		this.fluid = fluid;
	}

	@Override
	public void empty(double volume) {
    if(fluid == null)
      throw new EmptyTankException("No fluid in tank [" + name() + "]");
		if(volume < volumeOfContents)
			volumeOfContents -= volume;
		else
			volumeOfContents = 0.0;
	}

  @Override
	public void fill(double volume) {
    if(fluid == null)
      throw new EmptyTankException("No fluid in tank [" + name() + "]");
		if(volume >= capacity)
			volumeOfContents = capacity;
		else
			volumeOfContents += volume;
	}

	@Override
	public double getLevel() {
		return volumeOfContents / capacity;
	}

  @Override
  public double getVolumeLevel() {
    return volumeOfContents;
  }

  @Override
	public double getCapacity() {
		return capacity;
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getNominalPower(unit);
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getNominalCPUThroughput(unit);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick(double dt) {
	}

}

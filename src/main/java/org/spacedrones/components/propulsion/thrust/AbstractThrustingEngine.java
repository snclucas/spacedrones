package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.propulsion.AbstractEngine;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.SimpleLinearThrustProfile;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractThrustingEngine extends AbstractEngine implements ThrustingEngine {
	
	protected final ThrustProfile thrustProfile;
	private final double maximumThrust;

	public AbstractThrustingEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, engineVector, vectored);
		this.maximumThrust = maximumThrust;
		this.thrustProfile = new SimpleLinearThrustProfile("Linear thrust model");
	}

	
	public AbstractThrustingEngine(String name,
			BusComponentSpecification busResourceSpecification,
			double maximumThrust, ThrustProfile thrustModel,
			EngineVector engineVector, boolean vectored) {
		super(name, busResourceSpecification, engineVector,vectored);
		this.maximumThrust = maximumThrust;
		this.thrustProfile = thrustModel;
	}
	
	
	public BusRequirement callDrive(double requestedPowerLevel) {
		this.requestedPowerLevel = requestedPowerLevel;
		double requiredPowerInW = getRequiredPower(requestedPowerLevel, Unit.W);
		double requiredCPUThroughput = getRequiredCPUThroughput(requestedPowerLevel, Unit.MFLOPs);
		return new BusRequirement(requiredPowerInW, requiredCPUThroughput);
	}
	

	public BusRequirement callStop() {
		return callDrive(0.0);
	}
	

	@Override
	public ThrustProfile getThrustProfile() {
		return this.thrustProfile;
	}


	@Override
	public double[] getThrust(double[] velocity) {
		double thrust = maximumThrust * thrustProfile.getNormalizedThrust(powerLevel);
		double[] vector = engineVector.getVectorComponents();		
		return new double[]{thrust * vector[0], thrust * vector[1], thrust * vector[2]};
	}
	
	
	@Override
	public double getMaximumThrust() {
		return this.maximumThrust;
	}


	
	@Override
	public double getRequiredPower(double requiredPowerLevel, Unit unit) {
		double nominalPower = getBusResourceSpecification() .getNominalPower(unit);
		double maximumOperatingPower = getBusResourceSpecification() .getMaximumOperationalPower(unit);
		return nominalPower + (maximumOperatingPower-nominalPower) * thrustProfile.getNormalizedPower(requiredPowerLevel);
	}
	

	@Override
	public double getRequiredCPUThroughput(double requiredPowerLevel, Unit unit) {
		// The CPU throughput does not depend upon power level in this model
		double nominalCPU = getBusResourceSpecification() .getNominalCPUThroughout(unit);
		double maximumOperatingCPU = getBusResourceSpecification() .getMaximumOperationalCPUThroughput(unit);
		return nominalCPU + (maximumOperatingCPU-nominalCPU) * thrustProfile.getNormalizedCPU(requiredPowerLevel);
	}
	
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		return null;
	}

}

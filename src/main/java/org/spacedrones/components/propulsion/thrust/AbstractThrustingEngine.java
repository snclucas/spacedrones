package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.propulsion.AbstractEngine;
import org.spacedrones.components.propulsion.EngineVector;
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
		double requiredPower = getRequiredPower(requestedPowerLevel);
		double requiredCPUThroughput = getRequiredCPUThroughput(requestedPowerLevel);
		return new BusRequirement(requiredPower, requiredCPUThroughput);
	}
	
	
	@Override
	public TypeInfo getCategory() {
		return new TypeInfo("Engine");
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
	public double getRequiredPower(double requiredPowerLevel) {
		double nominalPower = busResourceSpecification.getNominalPower();
		double maximumOperatingPower = busResourceSpecification.getMaximumOperationalPower();
		return nominalPower + (maximumOperatingPower-nominalPower) * thrustProfile.getNormalizedPower(requiredPowerLevel);
	}
	

	@Override
	public double getRequiredCPUThroughput(double requiredPowerLevel) {
		// The CPU throughput does not depend upon power level in this model
		double nominalCPU = busResourceSpecification.getNominalCPUThroughout();
		double maximumOperatingCPU = busResourceSpecification.getMaximumOperationalCPUThroughput();
		return nominalCPU + (maximumOperatingCPU-nominalCPU) * thrustProfile.getNormalizedCPU(requiredPowerLevel);
	}
	
	
	@Override
	public SystemStatus runDiagnostics(int level) {
		return thrustProfile.runDiagnostics(level);
	}

}

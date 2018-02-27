package org.spacedrones.software;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.components.propulsion.ThrustDriveInterface;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.status.SystemStatusMessage;

import java.util.List;

public class PropulsionManagementSoftware extends AbstractSoftware implements Software, ThrustDriveInterface {

	public PropulsionManagementSoftware(String name) {
		super(name);
	}

	public SystemStatusMessage callDrive(double powerLevel) {
		SystemStatusMessage message = null;
		List<SpacecraftBusComponent> engines = getSystemComputer().getSystemComputer().findComponentByType(Engine.class);
		for(SpacecraftBusComponent engine : engines)
			if(engine instanceof ThrustingEngine) {
				message =    ((ThrustDriveInterface) engine).callDrive(powerLevel);
			}
		return message;
	}

	public SystemStatusMessage callDrive(double powerLevel, String engineIdent) {
		ThrustingEngine engine = findEngineByIdent(engineIdent);
		if(engine == null)
			return new SystemStatusMessage(this, "No engine found with ident:"+engineIdent,
              Status.CRITICAL);

		BusRequirement busRequirement = engine.callDrive(powerLevel);
		SystemStatusMessage operationPermittedMessage = getSystemComputer().getSystemComputer().requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			engine.execute();
			return new SystemStatusMessage(
					engine, "Engine [ident:"+engine.id() + "], power level set to " + powerLevel, Status.SUCCESS);
		}
		else
			return operationPermittedMessage;
	}

	public SystemStatusMessage callStop(String engineIdent) {
		return callDrive(0, engineIdent);
	}

	public SystemStatusMessage callStop() {
		return callDrive(0);
	}

	public SystemStatusMessage callVector(EngineVector engineVector, String engineIdent) {
		ThrustingEngine engine = findEngineByIdent(engineIdent);
		if(engine == null)
			return new SystemStatusMessage(null, "No engine found with id: "+engineIdent, Status.CRITICAL);

		BusRequirement busRequirement = engine.callVector(engineVector);
		SystemStatusMessage operationPermittedMessage = getSystemComputer().getSystemComputer().requestOperation(engine, busRequirement);

		if(operationPermittedMessage.getStatus() == Status.PERMITTED) {
			if(engine.isVectored()) {
				engine.execute();
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.id() + "], engine vector set to " + engineVector, Status.SUCCESS);
			}
			else {
				return new SystemStatusMessage(
						engine, "Engine [ident:"+engine.id() + "], cannot be vectored",
                Status.NOT_PERMITTED);
			}
		}
		else
			return operationPermittedMessage;
	}

	@Override
	public String getDescription() {
		return "Engine management";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}

	private ThrustingEngine findEngineByIdent(String ident) {
		List<SpacecraftBusComponent> engines = getSystemComputer().getSystemComputer().findComponentByType(Engine.class);
		//TODO LOOK at thisif(engines != null)
			for(SpacecraftBusComponent engine : engines) {
				if(engine.id() == ident)
					return (ThrustingEngine) engine;
			}
		return null;
	}

	@Override
	public String description() {
		return "Software to manage and control the propulsion systems.";
	}

}
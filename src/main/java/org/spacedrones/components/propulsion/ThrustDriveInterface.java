package org.spacedrones.components.propulsion;

import org.spacedrones.status.SystemStatusMessage;

public interface ThrustDriveInterface {
	//Basic engines interaction
	SystemStatusMessage callDrive(double powerLevel);
	SystemStatusMessage callDrive(double powerLevel, String engineIdent);
	SystemStatusMessage callStop(String engineIdent);
	SystemStatusMessage callStop();
	SystemStatusMessage callVector(EngineVector engineVector, String engineIdent);
}

package org.spacedrones.structures.hulls;

import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.status.SystemStatus;

public class SimpleMonocoqueHull extends AbstractHull {

	public SimpleMonocoqueHull(String name, HullSpecification hullSpecification, Hull.Type hullType) {
		super(name, hullSpecification, hullType);
	}

	@Override
	public double getCurrentPower(Unit unit) {
		// Nominal and operation power are the same for this hull
		return getNominalPower(unit);
	}

	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput(unit);
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + name() +"] OK", Status.OK);
		return systemStatus;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + name() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage);
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(name() + " online.", Status.OK);
		return systemStatus;
	}

}

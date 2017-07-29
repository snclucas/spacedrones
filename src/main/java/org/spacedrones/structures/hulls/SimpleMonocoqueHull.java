package org.spacedrones.structures.hulls;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.status.SystemStatus;

public class SimpleMonocoqueHull extends AbstractHull {
	public static TypeInfo typeID = new TypeInfo("SimpleMonocoqueHull");
	
	public SimpleMonocoqueHull(String name, HullSpecification hullSpecification, TypeInfo hullType) {
		super(name, hullSpecification, hullType);
	}
	

	@Override
	public TypeInfo getType() {
		return typeID;
	}

	@Override
	public String describe() {
		return "Simple Monocoque Hull ["+ hullSpecification.getName() +"]";
	}
	
	
	@Override
	public double getCurrentPower() {
		// Nominal and operation power are the same for this hull
		return getNominalPower();
	}
	

	@Override
	public double getCurrentCPUThroughput() {
		// Nominal and operation CPU are the same for this hull
		return getNominalCPUThroughput();
	}

	@Override
	public SystemStatus runDiagnostics(int level) {
		//Nothing really to diagnose with this simple hull
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getUniversalTime());
	}

	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		return systemStatus; 
	}
	
	

}

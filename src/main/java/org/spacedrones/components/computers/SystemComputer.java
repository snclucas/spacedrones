package org.spacedrones.components.computers;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.status.SystemStatusMessage;

import java.util.List;

public interface SystemComputer extends Computer {

	void registerSpacecraftComponent(SpacecraftBusComponent component);

	void setComponents(List<SpacecraftBusComponent> components);

	SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement);

	MessageMediator getMessagingSystem();
	void setMessagingSystem(MessageMediator messagingSystem);
	Object getSystemData(String id);

	SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, String status);

	List<SystemStatusMessage> getSystemMessages();

	List<SystemStatusMessage> checkSystems();

  <T extends Taxonomic> List<T> findComponentByType(Class<T> type);

	double getTotalCPUThroughputAvailable(Unit unit);
	double getTotalPowerAvailable(Unit unit);

	double getTotalCurrentPower(Unit unit);
	double getTotalCurrentCPUThroughput(Unit unit);

	List<Engine> getEngines();
	List<SystemComputer> getComputers();
	List<CommunicationComponent> getCommunicationDevices();

	String getVesselIdent();
}

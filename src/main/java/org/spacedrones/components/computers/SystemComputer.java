package org.spacedrones.components.computers;

import java.util.List;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.spacecraft.SpacecraftFirmware;
import org.spacedrones.status.SystemStatusMessage;

public interface SystemComputer extends Computer {

	static TypeInfo category() {
		return new TypeInfo("Computer");
	}

	static TypeInfo type() {
		return new TypeInfo("SystemComputer");
	}

	double getUniversalTime();

	SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement);

	MessageMediator getMessagingSystem();
	void setMessagingSystem(MessageMediator messagingSystem);
	Object getSystemData(String id);
	DataStore getStorageDevice();
	SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status);

	List<SystemStatusMessage> getSystemMessages();

	List<SystemStatusMessage> checkSystems();

	List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType) throws ComponentConfigurationException;
	List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory) throws ComponentConfigurationException;

	double getTotalCPUThroughputAvailable(Unit unit);
	double getTotalPowerAvailable(Unit unit);

	double getTotalCurrentPower(Unit unit);
	double getTotalCurrentCPUThroughput(Unit unit);

	List<Engine> getEngines();
	List<SystemComputer> getComputers();
	List<CommunicationComponent> getCommunicationDevices();
}

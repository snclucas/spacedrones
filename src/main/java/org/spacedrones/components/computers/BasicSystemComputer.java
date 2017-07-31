package org.spacedrones.components.computers;

import java.util.ArrayList;
import java.util.List;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.software.SystemMessageService;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

public class BasicSystemComputer extends AbstractSystemComputer implements SystemComputer {

	private List<SystemStatusMessage> systemMessages;


	public BasicSystemComputer(String name, BusComponentSpecification busResourceSpecification, 
			double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
		systemMessages = new ArrayList<SystemStatusMessage>();
	}

	
	
	








	public SystemStatus online() {
		SystemStatus status = super.online();
		// Scan spacecraft bus components and register with the messaging system
		status.mergeSystemMessages(scanSpacecraftBusForComponents());
		// Check all systems
		status.mergeSystemMessages(checkSystems());

		status.addSystemMessage(addSystemMessage(this, "Onlining spacecraft components", Status.OK));
		for(SpacecraftBusComponent component : spacecraftBus.getComponents()) {
			if(component != this) { // Ignore this computer
				SystemStatus busComponentStatus = component.online();
				status.mergeSystemStatus(busComponentStatus);
			}
		}

		if(status.hasCriticalMessages()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" cannot be onlined. Critical errors.", Status.CRITICAL));
			online = false;
		}
		else if(status.hasWarningMessages()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" online but with warnings.", Status.WARNING));
			online = true;
		}
		else if(status.isOK()) {
			status.addSystemMessage(addSystemMessage(this, this.getName() + 
					" online, no warnings or critical errors.", Status.INFO));
			online = true;
		}
		return status;
	}


	public List<SystemStatusMessage> scanSpacecraftBusForComponents() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		systemStatusMessages.add(new SystemStatusMessage(this, "Scanning spacecraft bus for components.", getUniversalTime(), Status.INFO));
		List<SystemStatusMessage> registerMessages = registerSpacecraftComponents(spacecraftBus.getComponents());
		systemStatusMessages.addAll(registerMessages);
		return systemStatusMessages;
	}



	@Override
	public void registerSpacecraftBus(Bus spacecraftBus) {
		this.spacecraftBus = spacecraftBus;
		addSystemMessage(this, "Spacecraft bus " + spacecraftBus.getName() + " registered with system computer [" + this.getName() + "]", Status.OK);
	}



	private List<SystemStatusMessage> registerSpacecraftComponents(List<SpacecraftBusComponent> components) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();

		for(SpacecraftBusComponent component : components) {
			//Set this as the registered computer in the component
			systemStatusMessages.add(component.registerSystemComputer(this));
			// Register the component with the messaging system
			systemStatusMessages.add(getMessagingSystem().addComponent(component));
		}

		return systemStatusMessages;
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something here
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		return systemStatus;
	}



	@Override
	public String describe() {
		return "System computer";
	}











	@Override
	public List<SystemStatusMessage> checkSystems() {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();

		double maximumAvailableVolume = getVolume(Unit.m3);

		SystemData data = new SystemData("maximumAvailableVolume", Double.toString(maximumAvailableVolume));
		storageDevice.saveData(new DataRecord("maximumAvailableVolume", data));

		Spacecraft spacecraft = spacecraftBus.getSpacecraft();

		if(spacecraft != null) {
			systemStatusMessages.add(
					new SystemStatusMessage(this, "Total vessel mass: " + 
							spacecraft.getTotalMassOfComponents(), getUniversalTime(), Status.INFO));
			systemStatusMessages.add(
					new SystemStatusMessage(this, "Available hull volume: " + 
			maximumAvailableVolume, getUniversalTime(), Status.INFO));
			systemStatusMessages.add(
					new SystemStatusMessage(this, "Required hull volume: " + 
			spacecraft.getTotalVolumeOfComponents(), getUniversalTime(), Status.INFO));
			if(maximumAvailableVolume < spacecraft.getTotalVolumeOfComponents())
				systemStatusMessages.add(
						new SystemStatusMessage(this, "Not enough space", getUniversalTime(), Status.CRITICAL));
		}

		systemStatusMessages.add(
				new SystemStatusMessage(this, "Available CPU throughput: " + getTotalCPUThroughputAvailable(Unit.MFLOP), getUniversalTime(), Status.INFO));
		systemStatusMessages.add(
				new SystemStatusMessage(this, "Required CPU throughput: " + getTotalCurrentCPUThroughput(Unit.MFLOP), getUniversalTime(), Status.INFO));

		if(getTotalCPUThroughputAvailable(Unit.MW) < getCurrentCPUThroughput(Unit.MW))
			systemStatusMessages.add(new SystemStatusMessage(this, "Not enough CPU", getUniversalTime(), Status.PROBLEM));




		return systemStatusMessages;
	}

	@Override
	public double getTotalCPUThroughputAvailable(Unit unit) {
		return 0;
	}


	@Override
	public SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status) {
		SystemStatusMessage systemStatusMessage = new SystemStatusMessage(component, message, getUniversalTime(), status);
		this.systemMessages.add(systemStatusMessage);
		return systemStatusMessage;
	}








	// -- Getters and setters -- //




	@Override
	public List<SystemStatusMessage> getSystemMessages() {
		return this.systemMessages;
	}



	@Override
	public MessageMediator getMessagingSystem() {
		return (MessageMediator)getSoftware(SystemMessageService.typeID);
	}


	@Override
	public void setMessagingSystem(MessageMediator messagingSystem) {
		loadSoftware(messagingSystem);
	}

	
	
		
	public static TypeInfo type() {
		return new TypeInfo("SystemComputer");
	}
	
	

}

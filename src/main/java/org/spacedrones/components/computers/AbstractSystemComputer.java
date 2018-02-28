package org.spacedrones.components.computers;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.software.SystemMessageService;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.spacecraft.SpacecraftFirmware;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSystemComputer extends AbstractComputer implements SystemComputer {

  private final List<SystemStatusMessage> systemMessages;

  private Bus spacecraftBus;

  private double totalPowerAvailable = Double.NEGATIVE_INFINITY;
  private double totalCPUThroughputAvailable = Double.NEGATIVE_INFINITY;

	AbstractSystemComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
    systemMessages = new ArrayList<>();
		setMessagingSystem(new SystemMessageService("Default messaging system"));
	}

  @Override
  public SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status) {
    SystemStatusMessage systemStatusMessage = new SystemStatusMessage(component, message, status);
    this.systemMessages.add(systemStatusMessage);
    return systemStatusMessage;
  }

  public String getVesselIdent() {
	  return id();
  }

  private List<SystemStatusMessage> scanSpacecraftBusForComponents() {
    List<SystemStatusMessage> systemStatusMessages = new ArrayList<>();
    systemStatusMessages.add(new SystemStatusMessage(this, "Scanning spacecraft bus for components.", Status.INFO));
    List<SystemStatusMessage> registerMessages = registerSpacecraftComponents(spacecraftBus.getComponents());
    systemStatusMessages.addAll(registerMessages);
    return systemStatusMessages;
  }


  @Override
  public List<SystemStatusMessage> getSystemMessages() {
    return this.systemMessages;
  }

  @Override
  public MessageMediator getMessagingSystem() {
    return (MessageMediator)getSoftware(SystemMessageService.class.getSimpleName());
  }

  @Override
  public void setMessagingSystem(MessageMediator messagingSystem) {
    loadSoftware(messagingSystem);
  }

	@Override
	public final void registerBus(Bus bus) {
		this.spacecraftBus = bus;
	}

  private List<SystemStatusMessage> registerSpacecraftComponents(List<SpacecraftBusComponent> components) {
    List<SystemStatusMessage> systemStatusMessages = new ArrayList<>();

    for(SpacecraftBusComponent component : components) {
      //Set this as the registered computer in the component
      systemStatusMessages.add(component.registerSystemComputer(this));
      // Register the component with the messaging system
      systemStatusMessages.add(getMessagingSystem().addComponent(component));

      System.out.println(component.name() + " " + component.getCurrentPower(Unit.kW));
    }

    return systemStatusMessages;
  }

  @Override
  public List<SystemStatusMessage> checkSystems() {
    List<SystemStatusMessage> systemStatusMessages = new ArrayList<>();

    systemStatusMessages.add(
            new SystemStatusMessage(this, "Available power: " + getTotalPowerAvailable(Unit.MW) + " " + Unit.MW.symbol(), Status.INFO));
    systemStatusMessages.add(
            new SystemStatusMessage(this, "Required power: " + getTotalCurrentPower(Unit.MW) + " " + Unit.MW.symbol(), Status.INFO));

    systemStatusMessages.add(
            new SystemStatusMessage(this, "Available CPU throughput: " + getTotalCPUThroughputAvailable(Unit.GFLOPs) + " " + Unit.GFLOPs.symbol(), Status.INFO));
    systemStatusMessages.add(
            new SystemStatusMessage(this, "Required CPU throughput: " + getTotalCurrentCPUThroughput(Unit.GFLOPs) + " " + Unit.GFLOPs.symbol(), Status.INFO));

    if(getTotalCPUThroughputAvailable(Unit.MW) < getCurrentCPUThroughput(Unit.MW))
      systemStatusMessages.add(new SystemStatusMessage(this, "Not enough CPU", Status.PROBLEM));

    return systemStatusMessages;
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
      status.addSystemMessage(addSystemMessage(this, this.name() +
              " cannot be onlined. Critical errors.", Status.CRITICAL));
      setOnline(false);
    }
    else if(status.hasWarningMessages()) {
      status.addSystemMessage(addSystemMessage(this, this.name() +
              " online but with warnings.", Status.WARNING));
      setOnline(true);
    }
    else if(status.isOK()) {
      status.addSystemMessage(addSystemMessage(this, this.name() +
              " online, no warnings or critical errors.", Status.INFO));
      setOnline(true);
    }
    return status;
  }

	@Override
	public Object getSystemData(String id) {
		return getStorageDevice().getData(id, SpacecraftData.class).get().getData();
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByType(Class<? extends SpacecraftBusComponent> component) throws ComponentConfigurationException {
		return spacecraftBus.findComponentByType(component);
	}

	@Override
	public SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement) {
		//Remove current component power and add back the new requested power
		double newBusPowerRequirement = getTotalCurrentPower(Unit.MW)
				- component.getCurrentPower(Unit.MW) + busRequirement.getPowerRequirement(Unit.MW);

		double newBusCPUThroughputRequirement = getTotalCurrentCPUThroughput(Unit.MFLOPs)
				- component.getCurrentCPUThroughput(Unit.MFLOPs) + busRequirement.getCPUThroughputRequirement(Unit.MFLOPs);

		if((newBusPowerRequirement > getTotalPowerAvailable(Unit.MW)))
			return new SystemStatusMessage(this, "Not enough bus power to perform operation, " +
		newBusPowerRequirement + " needed, " + getTotalPowerAvailable(Unit.MW) + " available",
              Status.NOT_ENOUGH_POWER);

		else if((newBusCPUThroughputRequirement > getTotalCPUThroughputAvailable(Unit.MFLOPs)))
			return new SystemStatusMessage(this, "Not enough bus CPU throughput to perform operation, " +
					newBusCPUThroughputRequirement + " needed, " + getTotalCPUThroughputAvailable(Unit.MFLOPs) + " available", Status.NOT_ENOUGH_CPU);
		else
			return new SystemStatusMessage(this, "Operation permitted", Status.PERMITTED);
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by computer: " + name() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage);
	}

	@Override
	public List<CommunicationComponent> getCommunicationDevices() {
		return SpacecraftFirmware.getCommunicationDevices(spacecraftBus);
	}

	@Override
	public List<Engine> getEngines() {
		return SpacecraftFirmware.getEngines(spacecraftBus);
	}

	@Override
	public List<SystemComputer> getComputers() {
		return SpacecraftFirmware.getComputers(spacecraftBus);
	}

  @Override
  public double getTotalCPUThroughputAvailable(Unit unit) {
    if(totalCPUThroughputAvailable != Double.NEGATIVE_INFINITY) {
      return totalCPUThroughputAvailable;
    }
    else{
      totalCPUThroughputAvailable = SpacecraftFirmware.getTotalCPUThroughputAvailable(spacecraftBus) / unit.value();
      return totalCPUThroughputAvailable;
    }
  }

  @Override
  public double getTotalPowerAvailable(Unit unit) {
    if(totalPowerAvailable != Double.NEGATIVE_INFINITY) {
      return totalPowerAvailable;
    }
    else{
      totalPowerAvailable = SpacecraftFirmware.getTotalPowerAvailable(spacecraftBus) / unit.value();
      return totalPowerAvailable;
    }
  }

	@Override
	public double getTotalCurrentPower(Unit unit) {
		return SpacecraftFirmware.getTotalCurrentPower(spacecraftBus, unit) / unit.value();
	}

	@Override
	public double getTotalCurrentCPUThroughput(Unit unit) {
		return SpacecraftFirmware.getTotalCurrentCPUThroughput(spacecraftBus, unit);
	}

}
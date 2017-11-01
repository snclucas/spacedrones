package org.spacedrones.components.computers;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
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
import java.util.Calendar;
import java.util.List;

public abstract class AbstractSystemComputer extends AbstractComputer implements SystemComputer {

	private final Bus spacecraftBus;

  private final List<SystemStatusMessage> systemMessages;

	AbstractSystemComputer(String name, Bus spacecraftBus, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification, maxCPUThroughput);
		this.spacecraftBus = spacecraftBus;
    systemMessages = new ArrayList<>();
		setMessagingSystem(new SystemMessageService("Default messaging system"));
	}


  @Override
  public void registerComponent(final SpacecraftBusComponent component) {
	  component.registerSystemComputer(this);
    spacecraftBus.register(component);
  }

  @Override
  public SystemStatusMessage addSystemMessage(SpacecraftBusComponent component, String message, Status status) {
    SystemStatusMessage systemStatusMessage = new SystemStatusMessage(component, message, getUniversalTime(), status);
    this.systemMessages.add(systemStatusMessage);
    return systemStatusMessage;
  }


  public List<SystemStatusMessage> scanSpacecraftBusForComponents() {
    List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
    systemStatusMessages.add(new SystemStatusMessage(this, "Scanning spacecraft bus for components.", getUniversalTime(), Status.INFO));
    List<SystemStatusMessage> registerMessages = registerSpacecraftComponents(getSpacecraftBus().getComponents());
    systemStatusMessages.addAll(registerMessages);
    return systemStatusMessages;
  }


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

  public SystemStatus online() {
    SystemStatus status = super.online();
    // Scan spacecraft bus components and register with the messaging system
    status.mergeSystemMessages(scanSpacecraftBusForComponents());
    // Check all systems
    status.mergeSystemMessages(checkSystems());

    status.addSystemMessage(addSystemMessage(this, "Onlining spacecraft components", Status.OK));
    for(SpacecraftBusComponent component : getSpacecraftBus().getComponents()) {
      if(component != this) { // Ignore this computer
        SystemStatus busComponentStatus = component.online();
        status.mergeSystemStatus(busComponentStatus);
      }
    }



    if(status.hasCriticalMessages()) {
      status.addSystemMessage(addSystemMessage(this, this.getName() +
              " cannot be onlined. Critical errors.", Status.CRITICAL));
      setOnline(false);
    }
    else if(status.hasWarningMessages()) {
      status.addSystemMessage(addSystemMessage(this, this.getName() +
              " online but with warnings.", Status.WARNING));
      setOnline(true);
    }
    else if(status.isOK()) {
      status.addSystemMessage(addSystemMessage(this, this.getName() +
              " online, no warnings or critical errors.", Status.INFO));
      setOnline(true);
    }
    return status;
  }

	@Override
	public Object getSystemData(String id) {
		return getStorageDevice().getData(id, SpacecraftData.category).getData();
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType) throws ComponentConfigurationException {
		return getSpacecraftBus().findComponentByType(componentType);
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory) throws ComponentConfigurationException {
		return getSpacecraftBus().findComponentByCategory(componentCategory);
	}

	@Override
	public SystemStatusMessage requestOperation(SpacecraftBusComponent component, BusRequirement busRequirement) {
		//Remove current component power and add back the new requested power
		double newBusPowerRequirement = getTotalCurrentPower(Unit.MW)
				- component.getCurrentPower(Unit.MW) + busRequirement.getPowerRequirement(Unit.MW);

		double newBusCPUThroughputRequirement = getTotalCurrentCPUThroughput(Unit.MFLOP)
				- component.getCurrentCPUThroughput(Unit.MFLOP) + busRequirement.getCPUThroughputRequirement(Unit.MFLOP);

		if((newBusPowerRequirement > getTotalPowerAvailable(Unit.MW)))
			return new SystemStatusMessage(this, "Not enough bus power to perform operation, " +
		newBusPowerRequirement + " needed, " + getTotalPowerAvailable(Unit.MW) + " available",
		getUniversalTime(), Status.NOT_ENOUGH_POWER);

		else if((newBusCPUThroughputRequirement > getTotalCPUThroughputAvailable(Unit.MFLOP)))
			return new SystemStatusMessage(this, "Not enough bus CPU throughput to perform operation, " +
					newBusCPUThroughputRequirement + " needed, " + getTotalCPUThroughputAvailable(Unit.MFLOP) + " available", getUniversalTime(), Status.NOT_ENOUGH_CPU);
		else
			return new SystemStatusMessage(this, "Operation permitted", getUniversalTime(), Status.PERMITTED);
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by computer: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}
	
	@Override
	public List<CommunicationComponent> getCommunicationDevices() {
		return SpacecraftFirmware.getCommunicationDevices(getSpacecraftBus());
	}

	@Override
	public List<Engine> getEngines() {
		return SpacecraftFirmware.getEngines(getSpacecraftBus());
	}

	@Override
	public List<SystemComputer> getComputers() {
		return SpacecraftFirmware.getComputers(getSpacecraftBus());
	}

	@Override
	public double getUniversalTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millisecond = cal.get(Calendar.MILLISECOND);
		
		//Change this;
		return ((year+0) + day/365.0 + 
				(hour/(365*24.0)) + 
				(minute/(365.0*24.0*60.0)) + 
				(second/(365.0*86400.0)) + 
				(millisecond/(365.0*86400000.0)));
	}
	
	@Override
	public double getTotalPowerAvailable(Unit unit) {
		return SpacecraftFirmware.getTotalPowerAvailable(getSpacecraftBus()) / unit.value();
	}

	@Override
	public double getTotalCurrentPower(Unit unit) {
		return SpacecraftFirmware.getTotalCurrentPower(getSpacecraftBus(), unit) / unit.value();
	}

	@Override
	public double getTotalCurrentCPUThroughput(Unit unit) {
		return SpacecraftFirmware.getTotalCurrentCPUThroughput(getSpacecraftBus(), unit);
	}


  // ----- Taxonomy

  @Override
  public TypeInfo type() {
    return new TypeInfo("SystemComputer");
  }

  @Override
  public TypeInfo category() {
    return type();
  }

}

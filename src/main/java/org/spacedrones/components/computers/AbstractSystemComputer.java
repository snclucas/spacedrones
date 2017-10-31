package org.spacedrones.components.computers;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.exceptions.ComponentConfigurationException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.software.SystemMessageService;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.BusRequirement;
import org.spacedrones.spacecraft.SpacecraftFirmware;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import java.util.Calendar;
import java.util.List;

public abstract class AbstractSystemComputer extends AbstractComputer implements SystemComputer {

	private final double maxCPUThroughput;

	private final DataStore storageDevice;


	AbstractSystemComputer(String name, BusComponentSpecification busResourceSpecification, double maxCPUThroughput) {
		super(name, busResourceSpecification);
		this.maxCPUThroughput = maxCPUThroughput;
		storageDevice = DataStoreFactory.getDataStore(DataStoreFactory.BASIC_DATASTORE);
		setMessagingSystem(new SystemMessageService("Default messaging system"));
	}

	@Override
	public Object getSystemData(String id) {
		return storageDevice.getData(id, SpacecraftData.category).getData();
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
	public SystemStatus online() {
		//SystemStatus systemStatus = super.online();
		SystemStatus systemStatus = new SystemStatus(this);

		if(getSpacecraftBus() == null) {
			systemStatus.addSystemMessage("No spacecraft bus found.", getUniversalTime(), Status.CRITICAL);
		}
		
		if(hasSoftware()) {
			systemStatus.addSystemMessage("No interface software loaded", getUniversalTime(), Status.WARNING);
		}
		return systemStatus;
	}

	@Override
	public DataStore getStorageDevice() {
		return storageDevice;
	}

	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by computer: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}

	public double getMaxCPUThroughput() {
		return maxCPUThroughput;
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

	public static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("SystemComputer");
	}

	public TypeInfo getType() {
		return type();
	}

	public TypeInfo getCategory() {
		return category();
	}

}

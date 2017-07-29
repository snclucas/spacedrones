package org.spacedrones.components.propulsion.thrust;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.storage.fuel.FuelStorageTank;


public class FuelSubSystem extends AbstractBusComponent implements SpacecraftBusComponent, BusCommunicator {
	public static TypeInfo typeID = new TypeInfo("FuelSubSystem");
	public static TypeInfo categoryID = new TypeInfo("FuelSubSystem");
	
	public static int PROPULSION_FUEL_SUBSYSTEM = 1;
	public static int REACTOR_FUEL_SUBSYSTEM = 2;
	
	
	
	public final static String BASIC_FUEL_SUBSYSTEM = "Basic fuel subsystem";

	protected List<FuelStorageTank> fuelTanks;
	
	protected int fuelSubsystemType;
	
	
	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}
	
	@Override
	public TypeInfo getType() {
		return typeID;
	}
	
	
	public FuelSubSystem(String name, BusComponentSpecification busResourceSpecification, int fuelSubsystemType) {
		super(name, busResourceSpecification);	
		this.fuelTanks = new ArrayList<FuelStorageTank>();
		this.fuelSubsystemType = fuelSubsystemType;
	}
	
	
	public FuelSubSystem(String name, BusComponentSpecification busResourceSpecification, 
			List<FuelStorageTank> fuelTanks, int fuelSubsystemType) {
		super(name, busResourceSpecification);
		
		this.fuelTanks = fuelTanks;
		this.fuelSubsystemType = fuelSubsystemType;
	}
	
	
	public int getFuelSubsystemType() {
		return fuelSubsystemType;
	}
	
	
//	public SystemStatusMessage registerSystemComputer(SystemComputer systemComputer) {
//		SystemStatusMessage systemStatusMessage = super.registerSystemComputer(systemComputer);
//		for(FuelStorageTank tank : fuelTanks)
//			tank.registerSystemComputer(systemComputer);
//		return systemStatusMessage;
//	}
	


	@Override
	public double getMass() {
		double totalMassOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalMassOfFuelTanks += tank.getMass();	
		return super.getMass() + totalMassOfFuelTanks;
	}
	
	
	@Override
	public double getVolume() {
		double totalVolumeOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalVolumeOfFuelTanks += tank.getVolume();	
		return super.getVolume() + totalVolumeOfFuelTanks;
	}


	public List<FuelStorageTank> getFuelTanks() {
		return Collections.unmodifiableList(fuelTanks);
	}
	
	
	public boolean hasFuelTanks() {
		return fuelTanks.size() > 0;
	}
	
	
	public void addFuelTank(FuelStorageTank fuelTank) {
		fuelTanks.add(fuelTank);
	}


	@Override
	public double getCurrentPower() {
		return busResourceSpecification.getNominalPower();
	}


	@Override
	public double getCurrentCPUThroughput() {
		return busResourceSpecification.getNominalCPUThroughout();
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Message recieveBusMessage(Message message) {
		String replyMessage = "Message recieved by: " + getName() + "\n " + message.getMessage();
		return new SystemMessage(null, this, replyMessage, getSystemComputer().getUniversalTime());
	}


	@Override
	public String describe() {
		return this.getName();
	}


	@Override
	public SystemStatus online() {
		SystemStatus systemStatus = new SystemStatus(this);
		systemStatus.addSystemMessage(getName() + " online.", getUniversalTime(), Status.OK);
		
		for(FuelStorageTank tank : fuelTanks)
			tank.online();
		
		return systemStatus; 
	}


	@Override
	public void tick() {
	
	}

}

package org.spacedrones.components.propulsion.thrust;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.BusCommunicator;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
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

	private List<FuelStorageTank> fuelTanks;
	
	private int fuelSubsystemType;
	
	
	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}
	
	@Override
	public TypeInfo getType() {
		return typeID;
	}
	
	
	FuelSubSystem(String name, BusComponentSpecification busResourceSpecification, int fuelSubsystemType) {
		super(name, busResourceSpecification);	
		this.fuelTanks = new ArrayList<>();
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
	public double getMass(Unit unit) {
		double totalMassOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalMassOfFuelTanks += tank.getMass(unit);
		return super.getMass(unit) + totalMassOfFuelTanks;
	}
	
	
	@Override
	public double getVolume(Unit unit) {
		double totalVolumeOfFuelTanks = 0.0;
		for(FuelStorageTank tank : fuelTanks)
			totalVolumeOfFuelTanks += tank.getVolume(unit);
		return super.getVolume(unit) + totalVolumeOfFuelTanks;
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
	public double getMaximumOperationalCPUThroughput(Unit unit) {
		return 0;
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return busResourceSpecification.getNominalPower(unit);
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return busResourceSpecification.getNominalCPUThroughout(unit);
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

package org.spacedrones.components.propulsion.thrust;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.*;
import org.spacedrones.software.Message;
import org.spacedrones.software.SystemMessage;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.storage.fuel.FuelStorageTank;


public class SimpleFuelSubSystem extends AbstractBusComponent implements FuelSubSystem {

	public static int PROPULSION_FUEL_SUBSYSTEM = 1;
	public static int REACTOR_FUEL_SUBSYSTEM = 2;

	private double fuelFlowRate = 0.0;

  private FuelConsumptionProfile fuelConsumptionProfile;

	public final static String BASIC_FUEL_SUBSYSTEM = "Basic fuel subsystem";

	private FuelStorageTank fuelTank = null;

	private int fuelSubsystemType;

  public SimpleFuelSubSystem(String name, BusComponentSpecification busResourceSpecification,
                             int fuelSubsystemType,
                             FuelConsumptionProfile fuelConsumptionProfile) {
    super(name, busResourceSpecification);

    this.fuelSubsystemType = fuelSubsystemType;
    this.fuelConsumptionProfile = fuelConsumptionProfile;
  }

	public SimpleFuelSubSystem(String name, BusComponentSpecification busResourceSpecification,
                             FuelStorageTank fuelTank, int fuelSubsystemType,
                             FuelConsumptionProfile fuelConsumptionProfile) {
		super(name, busResourceSpecification);

		this.fuelTank = fuelTank;
		this.fuelSubsystemType = fuelSubsystemType;
    this.fuelConsumptionProfile = fuelConsumptionProfile;
	}

  public void setFuelTank(FuelStorageTank fuelStorageTank) {
    this.fuelTank = fuelStorageTank;
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
		return super.getMass(unit) +  + ((fuelTank != null)
      ? fuelTank.getMass(unit)
      : 0.0);
	}


	@Override
	public double getVolume(Unit unit) {
		return super.getVolume(unit) + ((fuelTank != null)
						? fuelTank.getVolume(unit)
            : 0.0);
	}


	public FuelStorageTank getFuelTank() {
		return fuelTank;
	}


	public boolean hasFuelTank() {
		return fuelTank != null;
	}


	@Override
	public double getMaximumCPUThroughput(Unit unit) {
		return 0;
	}

	@Override
	public double getCurrentPower(Unit unit) {
		return getBusResourceSpecification() .getNominalPower(unit);
	}


	@Override
	public double getCurrentCPUThroughput(Unit unit) {
		return getBusResourceSpecification() .getNominalCPUThroughout(unit);
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
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
    fuelTank.online();
		return systemStatus;
	}


	@Override
	public void tick(double dt) {
    double volumeOfFuelUsed = dt * fuelFlowRate;
    fuelTank.removeFuelConstituent(volumeOfFuelUsed);
    fuelTank.tick(dt);
	}

  @Override
  public double getFuelFlowRate(double powerLevel, Unit unit) {
    return fuelConsumptionProfile.getNormalizedFuelConsumption(powerLevel) *
            (fuelConsumptionProfile.getMaxFlowRate(unit) - fuelConsumptionProfile.getMinFlowRate(unit));
  }

  @Override
  public void setFuelFlowRate(double flowRate, Unit unit) {
    if(unit.type() != Unit.Type.FLOWRATE) {
      throw new IllegalArgumentException("Flow rate needs to be in flow rate units");
    }
    this.fuelFlowRate = flowRate * unit.value();
  }
}

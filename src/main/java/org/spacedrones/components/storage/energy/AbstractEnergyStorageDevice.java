package org.spacedrones.components.storage.energy;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.comms.Status;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatus;

public abstract class AbstractEnergyStorageDevice extends AbstractBusComponent implements EnergyStorageDevice {
	
	private final double storageCapacity;
	private final double chargeRate;
	private final double dischargeRate;
  private double energyLevel;


  AbstractEnergyStorageDevice(String name,
                              BusComponentSpecification busResourceSpecification, double storageCapacity,
                              double chargeRate, double dischargeRate) {
		super(name, busResourceSpecification);
		this.storageCapacity = storageCapacity;
		this.chargeRate = chargeRate;
		this.dischargeRate = dischargeRate;
		this.energyLevel = 0.0;
	}

  @Override
  public SystemStatus online() {
    SystemStatus systemStatus = new SystemStatus(this);
    online = true;
    systemStatus.addSystemMessage(name() + " online.", Status.OK);
    return systemStatus;
  }

  @Override
  public SystemStatus runDiagnostics(int level) {
    return runDiagnostics(level);
  }


  @Override
  public double getEnergyLevel() {
    return this.energyLevel;
  }

  @Override
	public double getStorageCapacity() {
		return storageCapacity;
	}

  @Override
	public double getChargeRate() {
		return chargeRate;
	}

  @Override
	public double getDischargeRate() {
		return dischargeRate;
	}

	@Override
	public void tick(double dt) {
    double stepPowerInput = dt*getChargeRate();
    System.out.println(stepPowerInput + " " + (getStorageCapacity() - energyLevel));
    if(stepPowerInput <= Math.abs(getStorageCapacity() - energyLevel)) {
      this.energyLevel += isOnline() ? dt*getChargeRate() : 0.0;
    }
	}
	
}

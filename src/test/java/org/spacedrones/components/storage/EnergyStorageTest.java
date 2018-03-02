package org.spacedrones.components.storage;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.storage.energy.Capacitor;
import org.spacedrones.components.storage.energy.EnergyStorageDevice;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;

import static org.junit.Assert.assertEquals;

public class EnergyStorageTest {

  private SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

	@Test
	public void testEnergyStorage() {

    SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(LinearSensorArray.class.getSimpleName());

    String name = "TestCapacitor";
    BusComponentSpecification busResourceSpecification = data.getBusComponentSpecification();
    double storageCapacity = 1 * Unit.MW.value();
    double chargeRate = 100 * Unit.kW.value();
    double dischargeRate = 100 * Unit.kW.value();

    EnergyStorageDevice esd = new Capacitor(name, busResourceSpecification, storageCapacity, chargeRate, dischargeRate);
    esd.online();

    assertEquals("Should be online", true,esd.isOnline());
    assertEquals("Energy level should be 0", 0 ,esd.getEnergyLevel(), 0.0001);


    for(int i = 0;i <= 3000;i++) {
      esd.tick(1.0);
      System.out.println(esd.getEnergyLevel());
    }



  }

}


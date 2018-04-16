package org.spacedrones.structures.storage.propellant;

import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class FuelStorageTankFactory {

	public static Tank getFuelStorageTank(String tankType, double capacity){
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(tankType);

    switch (tankType) {
      case "GasStorageTank": {
        double tankMassOverheadFraction = 1.1;
        double volume = capacity * tankMassOverheadFraction;
        BusComponentSpecification busSpecs = data.getBusComponentSpecification();
        busSpecs.setVolume(volume);
        return new LiquidStorageTank(tankType, busSpecs, capacity);
      }
      case "LiquidStorageTank": {
        double tankMassOverheadFraction = 1.3;
        double volume = capacity * tankMassOverheadFraction;
        BusComponentSpecification busSpecs = data.getBusComponentSpecification();
        busSpecs.setVolume(volume);
        return new LiquidStorageTank(tankType, busSpecs, capacity);
      }
      default:
        throw new ItemNotFoundException("No fuel tank found with type: " + tankType);
    }

	}

}

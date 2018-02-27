package org.spacedrones.structures.storage.fuel;

import org.spacedrones.Configuration;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class FuelStorageTankFactory {
	
	public static FuelStorageTank getFuelStorageTank(String tankType, double capacity){
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(tankType);

		if(tankType.equals("LiquidStorageTank")){
			double tankMassOverheadFraction = 1.1;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new LiquidStorageTank(tankType, busSpecs, capacity);
		}
		else if(tankType.equals(CryogenicLiquidStorageTank.type)){
			double tankMassOverheadFraction = 1.3;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new CryogenicLiquidStorageTank(tankType, busSpecs, capacity);
		}
		else {
			throw new ItemNotFoundException("No fuel tank found with type: " + tankType);
		}
		
	}

}

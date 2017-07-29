package org.spacedrones.structures.storage.fuel;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.spacecraft.BusComponentSpecification;

public class FuelStorageTankFactory {
	
	public static FuelStorageTank getFuelStorageTank(TypeInfo tankType, double capacity){
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();
		
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(tankType);

		if(tankType.equals(LiquidStorageTank.type())){	
			double tankMassOverheadFraction = 1.1;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new LiquidStorageTank(tankType.toString(), busSpecs, capacity);
		}
		else if(tankType.equals(CryogenicLiquidStorageTank.type())){
			double tankMassOverheadFraction = 1.3;
			double volume = capacity * tankMassOverheadFraction;
			BusComponentSpecification busSpecs = data.getBusComponentSpecification();
			busSpecs.setVolume(volume);
			return new CryogenicLiquidStorageTank(tankType.toString(), busSpecs, capacity);
		}
		else {
			throw new ItemNotFoundException("No fuel tank found with type: " + tankType);
		}
		
	}

}

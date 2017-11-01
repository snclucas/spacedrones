package org.spacedrones.components.energygeneration;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Unit;

public class PowerGenerationFactory extends DataFactory {

	public static PowerGenerator getPowerGenerator(TypeInfo powerGenerationType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(powerGenerationType);
		
		if(powerGenerationType.equals(SimpleSolarArray.type)){
			double arrayArea = 1.0* Unit.m.value() * 15 * Unit.m.value();	
			
			double efficiency = 75 * Unit.percent.value();
			
			PowerGenerator solarArray = 
					new SimpleSolarArray("Simple Solar Array", data.getBusComponentSpecification(), 
							arrayArea, efficiency);

			return solarArray;
		}
		else if(powerGenerationType.equals(SubspacePowerExtractor.type)){
			double arrayArea = 1.0* Unit.m.value() * 15 * Unit.m.value();	
			double efficiency = 75 * Unit.percent.value();
			
			PowerGenerator solarArray = 
					new SubspacePowerExtractor("Sub ether extractor", data.getBusComponentSpecification(), arrayArea, efficiency);

			return solarArray;
		}

		return null;
	}

}

package org.spacedrones.components.energygeneration;

import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Unit;

import java.util.*;

public class PowerGenerationFactory extends DataFactory {

  @SuppressWarnings("unchecked")
	public static <T extends PowerGenerator> Optional<T> getPowerGenerator(String powerGenerationType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(powerGenerationType);

		if(Objects.equals(powerGenerationType, SimpleSolarArray.class.getSimpleName())){
			double arrayArea = 1.0* Unit.m.value() * 15 * Unit.m.value();
			double efficiency = 75 * Unit.percent.value();

      return Optional.of((T) new SimpleSolarArray("Simple Solar Array", data.getBusComponentSpecification(),
          arrayArea, efficiency));
		}
		else if(Objects.equals(powerGenerationType, SubspacePowerExtractor.class.getSimpleName())){
			double arrayArea = 10.0* Unit.m.value() * 150 * Unit.m.value();
			double efficiency = 75 * Unit.percent.value();

      return Optional.of((T) new SubspacePowerExtractor("Sub ether extractor", data.getBusComponentSpecification(), arrayArea, efficiency));
		}
		return Optional.empty();
	}

}

package org.spacedrones.components.propulsion;

import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.FuelConsumptionProfile;
import org.spacedrones.profiles.FuelConsumptionProfileFactory;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.profiles.ThrustProfileFactory;

public class EngineFactory extends DataFactory {

	public static ThrustingEngine getEngine(String engineType, boolean vectored){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(engineType);

		if(engineType.equals(SimpleIonEngine.type)){
			double maximumThrust = 1 * Unit.N.value();			

			ThrustProfile thrustAlgorithm = ThrustProfileFactory.getThrustAlgorithm(
					ThrustProfileFactory.SIMPLE_LINEAR);	
			
			FuelConsumptionProfile fuelConsumptionProfile = FuelConsumptionProfileFactory.getFuelConsumptionProfile(
					FuelConsumptionProfileFactory.SIMPLE_LINEAR);

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleIonEngine(
					SimpleIonEngine.type.toString(), data.getBusComponentSpecification(),
					maximumThrust, 
					thrustAlgorithm, fuelConsumptionProfile, engineVector, vectored);
		}
		else if(engineType.equals(SimpleThruster.type)){
			double maximumThrust = 1 * Unit.kN.value(); // N	

			ThrustProfile thrustAlgorithm = ThrustProfileFactory.getThrustAlgorithm(
					ThrustProfileFactory.SIMPLE_LINEAR);	
			
			FuelConsumptionProfile fuelConsumptionProfile = FuelConsumptionProfileFactory.getFuelConsumptionProfile(
					FuelConsumptionProfileFactory.SIMPLE_LINEAR);

			//Align along axis of spacecraft
			EngineVector engineVector = new EngineVector(1,0,0);

			return new SimpleThruster(
					SimpleThruster.type.toString(), data.getBusComponentSpecification(),
					maximumThrust, 
					thrustAlgorithm, fuelConsumptionProfile, engineVector, vectored);
		}
		return null;
	}

}

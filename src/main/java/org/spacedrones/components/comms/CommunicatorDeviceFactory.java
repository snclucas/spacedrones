package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.algorithm.SimpleRadioFrequencyPropagationModel;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Physics;
import org.spacedrones.physics.Unit;


public class CommunicatorDeviceFactory extends DataFactory {

	public static CommunicationComponent getCommunicator(TypeInfo commType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(commType);
		
		if(commType.equals(RadioCommunicator.type())) {
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("RF");
			CommunicationComponent communicationComponent = 
					new RadioCommunicator(RadioCommunicator.type().toString(), data.getBusComponentSpecification(), propagationModel);
			communicationComponent.setDeviceNoiseLevel(Physics.dBm2W(-80)); // -80 dBm
			communicationComponent.setEfficiency(90 * Unit.percent.value());
			return communicationComponent;
		}
		else if(commType.equals(SubSpaceCommunicator.type())){
			
			Model propagationModel = new SimpleRadioFrequencyPropagationModel("SUBSPACE");
			CommunicationComponent communicationComponent = 
					new SubSpaceCommunicator(SubSpaceCommunicator.type().toString(), data.getBusComponentSpecification(), propagationModel);
			return communicationComponent;
		}
		return null;
	}
	

}

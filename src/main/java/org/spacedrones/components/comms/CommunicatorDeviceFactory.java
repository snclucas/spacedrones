package org.spacedrones.components.comms;

import org.spacedrones.algorithm.Model;
import org.spacedrones.algorithm.SimpleRadioFrequencyPropagationModel;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Physics;
import org.spacedrones.physics.Unit;


public class CommunicatorDeviceFactory extends DataFactory {

	public static CommunicationComponent getCommunicator(String commType){
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(commType);

		if(commType.equals("RadioCommunicator")) {

			Model propagationModel = new SimpleRadioFrequencyPropagationModel("RF");
			CommunicationComponent communicationComponent =
					new RadioCommunicator(commType, data.getBusComponentSpecification(), propagationModel);
			communicationComponent.setDeviceNoiseLevel(Physics.dBm2W(-80)); // -80 dBm
			communicationComponent.setEfficiency(90 * Unit.percent.value());
			return communicationComponent;
		}
		else if(commType.equals("SubSpaceCommunicator")){

			Model propagationModel = new SimpleRadioFrequencyPropagationModel("SUBSPACE");
      return new SubSpaceCommunicator(commType, data.getBusComponentSpecification(), propagationModel);
		}
		return null;
	}


}

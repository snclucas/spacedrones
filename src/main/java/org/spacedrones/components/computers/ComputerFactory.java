package org.spacedrones.components.computers;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.software.PropulsionManagementSoftware;
import org.spacedrones.software.Software;
import org.spacedrones.software.SystemMessageService;
import org.spacedrones.spacecraft.SpacecraftBus;

public class ComputerFactory extends DataFactory {

	public static SystemComputer getComputer(TypeInfo computerType){
		
		if(computerType.equals(BasicSystemComputer.type)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(computerType);

			double maxCPUThroughput = 1000; //GFLOP

			SpacecraftBus bus = new SpacecraftBus();
			
			// Set spacecraft bus to null
			SystemComputer computer = new BasicSystemComputer("Simple System Computer", bus, data.getBusComponentSpecification(), maxCPUThroughput);
			
			Software engineSoftware = new PropulsionManagementSoftware("Engine software");
			computer.loadSoftware(engineSoftware);
			
			MessageMediator messagingSystem = new SystemMessageService("System message mediator software");
			computer.loadSoftware(messagingSystem);
			return computer;
		}

		throw new ItemNotFoundException("No computer found with type: " + computerType);
	}

}

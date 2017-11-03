package org.spacedrones.components.computers;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.software.PropulsionManagementSoftware;
import org.spacedrones.software.Software;
import org.spacedrones.software.SystemMessageService;

public class ComputerFactory extends DataFactory {

	public static SystemComputer getSystemComputer(TypeInfo computerType){
		
		if(computerType.equals(BasicSystemComputer.type)){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(Computer.type);

			double maxCPUThroughput = 1000 * Unit.GFLOPs.value(); //GFLOPs
			
			// Set spacecraft bus to null
			SystemComputer computer = new BasicSystemComputer("System Computer", data.getBusComponentSpecification(), maxCPUThroughput);
			
			Software engineSoftware = new PropulsionManagementSoftware("Engine software");
			computer.loadSoftware(engineSoftware);
			
			MessageMediator messagingSystem = new SystemMessageService("System message mediator software");
			computer.loadSoftware(messagingSystem);
			return computer;
		}
		throw new ItemNotFoundException("No computer found with type: " + computerType);
	}


  public static Computer getComputer(TypeInfo computerType){
    if(computerType.equals(AuxiliaryComputer.type)){
      SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(Computer.type);

      double maxCPUThroughput = 1000 * Unit.GFLOPs.value(); //GFLOPs

      // Set spacecraft bus to null
      Computer computer = new AuxiliaryComputer("Aux Computer", data.getBusComponentSpecification(), maxCPUThroughput);

      MessageMediator messagingSystem = new SystemMessageService("System message mediator software");
      computer.loadSoftware(messagingSystem);
      return computer;
    }
    throw new ItemNotFoundException("No computer found with type: " + computerType);
  }

}

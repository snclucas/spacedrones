package org.spacedrones.components.computers;

import org.spacedrones.data.DataFactory;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.exceptions.ItemNotFoundException;
import org.spacedrones.physics.Unit;
import org.spacedrones.software.MessageMediator;
import org.spacedrones.software.PropulsionManagementSoftware;
import org.spacedrones.software.Software;
import org.spacedrones.software.SystemMessageService;

import java.util.*;

public class ComputerFactory extends DataFactory {

	public static SystemComputer getSystemComputer(String computerType){

		if(Objects.equals(computerType, BasicSystemComputer.class.getSimpleName())){
			SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(Computer.class.getSimpleName());

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


  public static Computer getComputer(String computerType){
    if(computerType.equals("AuxiliaryComputer")){
      SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(Computer.class.getSimpleName());

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

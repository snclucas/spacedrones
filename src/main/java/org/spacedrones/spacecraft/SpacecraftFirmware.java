package org.spacedrones.spacecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import org.spacedrones.components.Component;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.status.SystemStatusMessage;

public class SpacecraftFirmware {

	public static boolean bootstrapSystemComputer(Bus bus) { 
		int systemComputerIndex = findSystemComputerIndex(bus);
		boolean hasSystemComputer = systemComputerIndex >= 0;
		if(hasSystemComputer) {
			SystemComputer systemComputer = (SystemComputer) bus.getComponents().get(systemComputerIndex);
			systemComputer.registerSpacecraftBus(bus);
		}
		return hasSystemComputer;
	}
	
	
	public static List<SpacecraftBusComponent> findBusComponentByCategory(Bus bus, TypeInfo componentCategory) {
		return bus.getComponents().stream()
				 .filter(x->x.getCategory().toString().equals(componentCategory.toString()))
				 .collect(Collectors.toList());
	}
	
	
	public static List<SpacecraftBusComponent> findBusComponentByType(Bus bus, TypeInfo componentType) {
		return bus.getComponents().stream()
				 .filter(x->x.getType().toString().equals(componentType.toString()))
				 .collect(Collectors.toList());
	}

	
	private static int findSystemComputerIndex(Bus bus) {
		List<SpacecraftBusComponent> components = bus.getComponents();
		for(int i = 0; i<components.size();i++ ) {
			if(components.get(i) instanceof SystemComputer)
				return i;
		}
		return -1;
	}


	public static List<SystemStatusMessage> scanSpacecraftComponents(Bus bus) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<SystemStatusMessage>();
		for(Component component : bus.getComponents())
			((SpacecraftBusComponent)component).accept(bus);
		return systemStatusMessages;
	}


	public static List<SystemComputer> getComputers(Bus bus) {
		List<SystemComputer> computers = new ArrayList<SystemComputer>();		
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof SystemComputer)
				computers.add((SystemComputer)component);
		return computers;
	}

	
	
	public static List<PowerGenerator> getPowerGenerators(Bus bus) {
		List<PowerGenerator> powerGenerators = new ArrayList<PowerGenerator>();		
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof PowerGenerator)
				powerGenerators.add((PowerGenerator)component);
		return powerGenerators;
	}
	

	public static List<CommunicationComponent> getCommunicationDevices(Bus bus) {
		List<CommunicationComponent> communicationComponents = new ArrayList<CommunicationComponent>();		
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof CommunicationComponent)
				communicationComponents.add((CommunicationComponent)component);
		return communicationComponents;
	}


	public static List<Engine> getEngines(Bus bus) {
		List<Engine> engines = new ArrayList<Engine>();		
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof Engine)
				engines.add((Engine)component);
		return engines;
	}


	public static double getTotalPowerAvailable(Bus bus) {
		double sumOfAvailablePowerFromGenerators = 0.0;
		for(Component component : bus.getComponents())
			if(component instanceof PowerGenerator)
				sumOfAvailablePowerFromGenerators += ((PowerGenerator)component).getMaximumPowerOutput();
		return sumOfAvailablePowerFromGenerators;
	}

	
	public double getTotalMaximumPowerOfComponents(Bus bus) {
		double sumOfAvailablePowerFromGenerators = 0.0;
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof PowerGenerator)
				sumOfAvailablePowerFromGenerators += ((PowerGenerator)component).getMaximumPowerOutput();
		return sumOfAvailablePowerFromGenerators;
	}
	

	public static double getTotalCPUThroughputAvailable(Bus bus) {
		double sumOfAvailableCPUThroughputFromComputers = 0.0;
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof SystemComputer)
				sumOfAvailableCPUThroughputFromComputers += ((SystemComputer)component).getMaxCPUThroughput();
		return sumOfAvailableCPUThroughputFromComputers;
	}
	
	
	public static double getTotalCurrentPower(Bus bus) {
		return bus.getComponents().stream().mapToDouble((ToDoubleFunction<? super Component>)d->((SpacecraftBusComponent)d).getCurrentPower()).sum();
	}

	
	public static double getTotalCurrentCPUThroughput(Bus bus) {
		return bus.getComponents().stream().mapToDouble(d->((SpacecraftBusComponent)d).getCurrentCPUThroughput()).sum();
	}

}



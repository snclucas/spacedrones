package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class SpacecraftFirmware {

	private static BiPredicate<SpacecraftBusComponent, Class<? extends SpacecraftBusComponent>> dd = new IsComponentOfType();

	static boolean bootstrapSystemComputer(Bus bus) {
		int systemComputerIndex = findSystemComputerIndex(bus);
		boolean hasSystemComputer = systemComputerIndex >= 0;
		if(hasSystemComputer) {
			SystemComputer systemComputer = (SystemComputer) bus.getComponents().get(systemComputerIndex);
		}
		return hasSystemComputer;
	}

	static List<SpacecraftBusComponent> findBusComponentByType(Bus bus, Class<? extends SpacecraftBusComponent> component) {
		return bus.getComponents().stream()
				 .filter(c -> dd.test(c, component))
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

	static List<SystemStatusMessage> scanSpacecraftComponents(Bus bus) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<>();
		for(SpacecraftBusComponent component : bus.getComponents()) {
			// Do something maybe?
		}
		return systemStatusMessages;
	}

	public static List<SystemComputer> getComputers(Bus bus) {
		List<SystemComputer> computers = new ArrayList<>();
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof SystemComputer)
				computers.add((SystemComputer)component);
		return computers;
	}

	public static List<PowerGenerator> getPowerGenerators(Bus bus) {
		List<PowerGenerator> powerGenerators = new ArrayList<>();
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof PowerGenerator)
				powerGenerators.add((PowerGenerator)component);
		return powerGenerators;
	}

	public static List<CommunicationComponent> getCommunicationDevices(Bus bus) {
		List<CommunicationComponent> communicationComponents = new ArrayList<>();
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof CommunicationComponent)
				communicationComponents.add((CommunicationComponent)component);
		return communicationComponents;
	}

	public static List<Engine> getEngines(Bus bus) {
		List<Engine> engines = new ArrayList<>();
		for(SpacecraftBusComponent component : bus.getComponents())
			if(component instanceof Engine)
				engines.add((Engine)component);
		return engines;
	}

	public static double getTotalPowerAvailable(Bus bus) {
    return bus.getComponents().stream()
            .filter(PowerGenerator.class::isInstance)
            .map(PowerGenerator.class::cast)
            .mapToDouble(PowerGenerator::getMaximumPowerOutput).sum();
	}

	public static double getTotalCPUThroughputAvailable(Bus bus) {
		return bus.getComponents().stream()
            .filter(Computer.class::isInstance)
            .map(Computer.class::cast)
            .mapToDouble(Computer::getMaxCPUThroughput).sum();
	}

	public static double getTotalCurrentPower(Bus bus, Unit unit) {
		return bus.getComponents().stream().mapToDouble(d->d.getCurrentPower(unit)).sum();
	}

	public static double getTotalCurrentCPUThroughput(Bus bus, Unit unit) {
		return bus.getComponents().stream().mapToDouble(d-> d.getCurrentCPUThroughput(unit)).sum();
	}

}



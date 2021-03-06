package org.spacedrones.spacecraft;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class SpacecraftFirmware {

	//private static BiPredicate<SpacecraftBusComponent, Class<? extends SpacecraftBusComponent>> dd = new IsComponentOfType();

  private static BiPredicate<Taxonomic, Class<? extends Taxonomic>> dd =
          (lhs, rhs)  -> lhs.getClass() == rhs ||
                  rhs.isAssignableFrom(lhs.getClass()) ||
                  Arrays.stream(lhs.getClass().getInterfaces()).anyMatch(s -> s == rhs);


  static SystemComputer getSystemComputer(List<SpacecraftBusComponent> components) {
    int systemComputerIndex = findSystemComputerIndex(components);
    boolean hasSystemComputer = systemComputerIndex >= 0;
    if(hasSystemComputer) {
      return (SystemComputer) components.get(systemComputerIndex);
    } else {
      return null;
    }
  }

  static boolean bootstrapSystemComputer(List<SpacecraftBusComponent> components) {
		int systemComputerIndex = findSystemComputerIndex(components);
		boolean hasSystemComputer = systemComputerIndex >= 0;
		if(hasSystemComputer) {
			SystemComputer systemComputer = (SystemComputer) components.get(systemComputerIndex);
		}
		return hasSystemComputer;
	}

	public static <T extends Taxonomic> List<T> findBusComponentByType(List<SpacecraftBusComponent> components, Class<T> type) {
    List<T> result = new ArrayList<>();

    for (SpacecraftBusComponent component : components) {
      if (dd.test(component, type)) {
        result.add((T) component);
      }
    }
    return result;
	}

	private static int findSystemComputerIndex(List<SpacecraftBusComponent> components) {
		for(int i = 0; i<components.size();i++ ) {
			if(components.get(i) instanceof SystemComputer)
				return i;
		}
		return -1;
	}

	static List<SystemStatusMessage> scanSpacecraftComponents(List<SpacecraftBusComponent> components) {
		List<SystemStatusMessage> systemStatusMessages = new ArrayList<>();
		for(SpacecraftBusComponent component : components) {
			// Do something maybe?
		}
		return systemStatusMessages;
	}

	public static List<SystemComputer> getComputers(List<SpacecraftBusComponent> components) {
		List<SystemComputer> computers = new ArrayList<>();
		for(SpacecraftBusComponent component : components)
			if(component instanceof SystemComputer)
				computers.add((SystemComputer)component);
		return computers;
	}

	public static List<PowerGenerator> getPowerGenerators(List<SpacecraftBusComponent> components) {
		List<PowerGenerator> powerGenerators = new ArrayList<>();
		for(SpacecraftBusComponent component : components)
			if(component instanceof PowerGenerator)
				powerGenerators.add((PowerGenerator)component);
		return powerGenerators;
	}

	public static List<CommunicationComponent> getCommunicationDevices(List<SpacecraftBusComponent> components) {
		List<CommunicationComponent> communicationComponents = new ArrayList<>();
		for(SpacecraftBusComponent component : components)
			if(component instanceof CommunicationComponent)
				communicationComponents.add((CommunicationComponent)component);
		return communicationComponents;
	}

	public static List<Engine> getEngines(List<SpacecraftBusComponent> components) {
		List<Engine> engines = new ArrayList<>();
		for(SpacecraftBusComponent component : components)
			if(component instanceof Engine)
				engines.add((Engine)component);
		return engines;
	}

	public static double getTotalPowerAvailable(List<SpacecraftBusComponent> components, Unit unit) {
    return components.stream()
            .filter( pg -> dd.test(pg,PowerGenerator.class) )
            .map(PowerGenerator.class::cast)
            .mapToDouble(d -> d.getPowerOutput(unit)).sum();
	}

	public static double getTotalCPUThroughputAvailable(List<SpacecraftBusComponent> components, Unit unit) {
		return components.stream()
            .filter( pg -> dd.test(pg,Computer.class) )
            .map(Computer.class::cast)
            .mapToDouble(d -> d.getCPUThroughputAvailable(unit)).sum();
	}

	public static double getTotalCurrentPower(List<SpacecraftBusComponent> components, Unit unit) {
		return components.stream().mapToDouble(d->d.getCurrentPower(unit)).sum();
	}

	public static double getTotalCurrentCPUThroughput(List<SpacecraftBusComponent> components, Unit unit) {
		return components.stream().mapToDouble(d-> d.getCurrentCPUThroughput(unit)).sum();
	}

}



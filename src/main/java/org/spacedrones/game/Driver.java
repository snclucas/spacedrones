package org.spacedrones.game;


import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerationFactory;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorFactory;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftBuildManager;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

import java.util.stream.Collectors;

public class Driver {

  Spacecraft spacecraft = null;

	public Driver() {
    Hull hull = HullFactory.getHull("Shuttle");
		SpacecraftBuildManager sbm = new SpacecraftBuildManager("Test", hull);

    SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());
    sbm.addComponent(systemComputer);

    Computer auxComputer = ComputerFactory.getComputer("AuxiliaryComputer");
    sbm.addComponent(auxComputer);

    PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.class.getSimpleName()).get();
    sbm.addComponent(powerGenerator);

    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.class.getSimpleName(), SensorType.RADAR, 1);
    sbm.addComponent(sensor);

    spacecraft = sbm.getSpacecraft();






	}

	public String online() {
    SystemStatus sysStatus = spacecraft.online();
    return sysStatus.getMessages()
            .stream().map(m-> m.getUniversalDate() + " " + m.getMessage())
            .collect( Collectors.joining( System.lineSeparator() ) );
  }

	public static void main(String args[]) {
		new Driver();
	}

}

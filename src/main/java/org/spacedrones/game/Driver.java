package org.spacedrones.game;


import org.spacedrones.components.computers.*;
import org.spacedrones.components.energygeneration.*;
import org.spacedrones.components.sensors.*;
import org.spacedrones.spacecraft.*;
import org.spacedrones.status.*;
import org.spacedrones.structures.hulls.*;

public class Driver {


	public Driver() {
    Hull hull = HullFactory.getHull("Shuttle");
		SpacecraftBuildManager sbm = new SpacecraftBuildManager("Test", hull);

    SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.type);
    sbm.addComponent(systemComputer);

    Computer auxComputer = ComputerFactory.getComputer(AuxiliaryComputer.type);
    sbm.addComponent(auxComputer);

    PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type);
    sbm.addComponent(powerGenerator);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type, SensorType.RADAR, 1);
    sbm.addComponent(sensor);

    Spacecraft spacecraft = sbm.getSpacecraft();

    SystemStatus sysStatus = spacecraft.online();

    sysStatus.getMessages().forEach(m-> System.out.println(m.getUniversalDate() + " " + m.getMessage()));


	}

	public static void main(String args[]) {
		new Driver();
	}

}

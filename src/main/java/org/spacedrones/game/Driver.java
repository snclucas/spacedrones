package org.spacedrones.game;


import org.spacedrones.components.computers.*;
import org.spacedrones.components.energygeneration.*;
import org.spacedrones.components.sensors.*;
import org.spacedrones.spacecraft.*;
import org.spacedrones.status.*;
import org.spacedrones.structures.hulls.*;

public class Driver {


	private Driver() {
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

    Spacecraft spacecraft = sbm.getSpacecraft();

    SystemStatus sysStatus = spacecraft.online();

    sysStatus.getMessages().forEach(m-> System.out.println(m.getUniversalDate() + " " + m.getMessage()));





	}

	public static void main(String args[]) {
		new Driver();
	}

}

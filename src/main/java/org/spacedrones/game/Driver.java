package org.spacedrones.game;


import org.spacedrones.components.computers.*;
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

    Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type, SensorType.RADAR, 1);
    sbm.addComponent(sensor);

    Spacecraft spacecraft = sbm.getSpacecraft();

    SystemStatus sysStatus = spacecraft.online();

    sysStatus.getMessages().stream().forEach(m-> System.out.println(m.getMessage()));


	}
	
	public static void main(String args[]) {
		new Driver();
	}

}

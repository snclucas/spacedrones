package org.spacedrones.software;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerationFactory;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.components.propulsion.EngineFactory;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.components.propulsion.thrust.FuelConsumingEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.SpacecraftBuildManager;
import org.spacedrones.status.SystemStatusMessage;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

import static org.junit.Assert.assertEquals;

public class PropulsionManagementSoftwareTest {

	private SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();

  private Hull hull = HullFactory.getHull("Shuttle");

	@Test
	public void testEngineManagementSoftwareNoEngine() {

		SpacecraftBuildManager spacecraftBuildManager = new SpacecraftBuildManager("Test", hull);

		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.class.getSimpleName());

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.class.getSimpleName()).get();
    spacecraftBuildManager.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOPs.value());
    spacecraftBuildManager.addComponent(computer);


		PropulsionManagementSoftware engineManagementSoftware =
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);

		SystemStatusMessage systemMsg = engineManagementSoftware.callDrive(34, "23");
		assertEquals("No critical error status returned for drive", Status.CRITICAL, systemMsg.getStatus());

		SystemStatusMessage systemMsg2 = engineManagementSoftware.callStop("34");
		assertEquals("No critical error status returned for stop", Status.CRITICAL, systemMsg2.getStatus());

		SystemStatusMessage systemMsg3 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), "34");
		assertEquals("No critical error status returned for vector", Status.CRITICAL, systemMsg3.getStatus());

	}



	@Test
	public void testEngineManagementSoftware() {
   // SpacecraftBuildManager spacecraftBuildManager = new SpacecraftBuildManager("Test", hull);

		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.class.getSimpleName());

		// Setup spacecraft bus
		//Hull hull = HullFactory.getHull("Shuttle");
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOPs.value());

    System.out.println(computer.getSystemStats(Unit.MW, Unit.GFLOPs));


    //Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", "1", hull);
		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.class.getSimpleName()).get();
    computer.registerSpacecraftComponent(powerGenerator);

    System.out.println(computer.getSystemStats(Unit.MW, Unit.GFLOPs));

    computer.online();

    System.out.println(computer.isOnline());

    System.out.println(computer.getSystemStats(Unit.MW, Unit.GFLOPs));

   // spacecraftBuildManager.addComponent(powerGenerator);

		// Simple computer
	//	spacecraftBuildManager.addComponent(computer);

		//for(SpacecraftBusComponent component : computer.getSpacecraftBus().getComponents()) {
		//	System.out.println(component.name() + " " +  component.getNominalPower(Unit.MW) + " " + component.getNominalCPUThroughput(Unit.MFLOPs));
		//}


    System.out.println(powerStatus("No drive", null, computer));

    PropulsionManagementSoftware engineManagementSoftware =
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);

		FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.class.getSimpleName(), false);
    //spacecraftBuildManager.addComponent(engine);
    computer.registerSpacecraftComponent(engine);

		double powerLevel = 34.45 * Unit.percent.value();
		SystemStatusMessage systemMsg4 = engineManagementSoftware.callDrive(powerLevel, engine.id());
		assertEquals("Critical error status returned for drive", Status.SUCCESS, systemMsg4.getStatus());
		assertEquals("Engine power level incorrect", powerLevel, engine.getPowerLevel(), 0.0001);

    System.out.println(powerStatus("Drive", engine, computer));
    System.out.println("---------------------------");
    System.out.println(computer.getSystemStats(Unit.MW, Unit.GFLOPs));


		SystemStatusMessage systemMsg5 = engineManagementSoftware.callStop(engine.id());
		assertEquals("Critical error status returned for stop", Status.SUCCESS, systemMsg5.getStatus());
		assertEquals("Engine power level incorrect", 0.0, engine.getPowerLevel(), 0.0001);

    System.out.println("---------------------------");
    System.out.println(powerStatus("Stop", engine, computer));
    System.out.println("---------------------------");
    System.out.println(computer.getSystemStats(Unit.MW, Unit.GFLOPs));
    System.out.println("---------------------------");

    SystemStatusMessage systemMsg6 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine.id());
		assertEquals("Critical error status returned for vector", Status.NOT_PERMITTED, systemMsg6.getStatus()); // passed false to engine factory

		FuelConsumingEngine engine2 = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.class.getSimpleName(), true);
    //spacecraftBuildManager.addComponent(engine2);
    computer.registerSpacecraftComponent(engine2);

		SystemStatusMessage systemMsg7 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine2.id());
		assertEquals("Critical error status returned for vector", Status.SUCCESS, systemMsg7.getStatus()); // passed true to engine factory

	}

	private String powerStatus(String text, Engine engine, SystemComputer computer) {
	  double enginePowerLevel = (engine != null) ? engine.getPowerLevel() : 0.0;
	  return text + " [" + enginePowerLevel +  "] " + computer.getTotalCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW);
  }

}

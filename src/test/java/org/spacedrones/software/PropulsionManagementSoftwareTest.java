package org.spacedrones.software;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerationFactory;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.EngineFactory;
import org.spacedrones.components.propulsion.EngineVector;
import org.spacedrones.components.propulsion.thrust.FuelConsumingEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.*;
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

		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type);

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type);
    spacecraftBuildManager.addComponent(powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOPs.value());
    spacecraftBuildManager.addComponent(computer);


		PropulsionManagementSoftware engineManagementSoftware =
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);



		assertEquals("Engine Management Software category incorrect", PropulsionManagementSoftware.category, engineManagementSoftware.category());
		assertEquals("Engine Management Software type incorrect", PropulsionManagementSoftware.type, engineManagementSoftware.type());

		SystemStatusMessage systemMsg = engineManagementSoftware.callDrive(34, "23");
		assertEquals("No critical error status returned for drive", Status.CRITICAL, systemMsg.getStatus());

		SystemStatusMessage systemMsg2 = engineManagementSoftware.callStop("34");
		assertEquals("No critical error status returned for stop", Status.CRITICAL, systemMsg2.getStatus());

		SystemStatusMessage systemMsg3 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), "34");
		assertEquals("No critical error status returned for vector", Status.CRITICAL, systemMsg3.getStatus());

	}



	@Test
	public void testEngineManagementSoftware() {
    SpacecraftBuildManager spacecraftBuildManager = new SpacecraftBuildManager("Test", hull);

		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type);



		// Setup spacecraft bus
		Hull hull = HullFactory.getHull("Shuttle");
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOPs.value());

		Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", "1", hull, new SpacecraftBus(computer));
		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type);
    spacecraftBuildManager.addComponent(powerGenerator);

		// Simple computer
		spacecraftBuildManager.addComponent(computer);

		//for(SpacecraftBusComponent component : computer.getSpacecraftBus().getComponents()) {
		//	System.out.println(component.getName() + " " +  component.getNominalPower(Unit.MW) + " " + component.getNominalCPUThroughput(Unit.MFLOPs));
		//}


		System.out.println("No drive " + computer.getTotalCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW)
			+ " : "	+ computer.getCurrentCPUThroughput(Unit.MFLOPs) + " " + computer.getTotalCPUThroughputAvailable(Unit.MFLOPs));

		PropulsionManagementSoftware engineManagementSoftware =
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);

		FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type, false);
    spacecraftBuildManager.addComponent(engine);

		double powerLevel = 34.45 * Unit.percent.value();
		SystemStatusMessage systemMsg4 = engineManagementSoftware.callDrive(powerLevel, engine.getId());
		assertEquals("Critical error status returned for drive", Status.SUCCESS, systemMsg4.getStatus());
		assertEquals("Engine power level incorrect", powerLevel, engine.getPowerLevel(), 0.0001);

		System.out.println("Drive [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW));

		SystemStatusMessage systemMsg5 = engineManagementSoftware.callStop(engine.getId());
		assertEquals("Critical error status returned for stop", Status.SUCCESS, systemMsg5.getStatus());
		assertEquals("Engine power level incorrect", 0.0, engine.getPowerLevel(), 0.0001);

		System.out.println("Stop [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW));

		SystemStatusMessage systemMsg6 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine.getId());
		assertEquals("Critical error status returned for vector", Status.NOT_PERMITTED, systemMsg6.getStatus()); // passed false to engine factory

		FuelConsumingEngine engine2 = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type, true);
    spacecraftBuildManager.addComponent(engine2);

		SystemStatusMessage systemMsg7 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine2.getId());
		assertEquals("Critical error status returned for vector", Status.SUCCESS, systemMsg7.getStatus()); // passed true to engine factory

	}

}

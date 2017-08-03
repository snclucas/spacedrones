package org.spacedrones.software;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
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

public class PropulsionManagementSoftwareTest {
	SpacecraftDataProvider spacecraftDataProvider =  Configuration.getSpacecraftDataProvider();


	// Setup spacecraft bus
	Hull hull = HullFactory.getHull("Shuttle");
	Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);
	Bus spacecraftBus = new SpacecraftBus("Spacecraft bus",spacecraft); 
	



	@Test
	public void testEngineManagementSoftwareNoEngine() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type());

		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type());
		SpacecraftBuildManager.addComponent(spacecraft, powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP.value());
		SpacecraftBuildManager.addComponent(spacecraft, computer);
		
		
		PropulsionManagementSoftware engineManagementSoftware = 
				new PropulsionManagementSoftware("Test EngineManagementSoftware");
		
		computer.loadSoftware(engineManagementSoftware);

		
		
		assertEquals("Engine Management Software category incorrect", PropulsionManagementSoftware.categoryID, engineManagementSoftware.getCategory());
		assertEquals("Engine Management Software type incorrect", PropulsionManagementSoftware.typeID, engineManagementSoftware.getType());

		SystemStatusMessage systemMsg = engineManagementSoftware.callDrive(34, "23");
		assertEquals("No critical error status returned for drive", Status.CRITICAL, systemMsg.getStatus());

		SystemStatusMessage systemMsg2 = engineManagementSoftware.callStop("34");
		assertEquals("No critical error status returned for stop", Status.CRITICAL, systemMsg2.getStatus());

		SystemStatusMessage systemMsg3 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), "34");
		assertEquals("No critical error status returned for vector", Status.CRITICAL, systemMsg3.getStatus());

	}



	@Test
	public void testEngineManagementSoftware() {
		SpacecraftComponentData data = spacecraftDataProvider.getComponentParameters(BasicSystemComputer.type());



		// Setup spacecraft bus
		Hull hull = HullFactory.getHull("Shuttle");
		Spacecraft spacecraft = new SimpleSpacecraft("Shuttle", hull);
		PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type());
		SpacecraftBuildManager.addComponent(spacecraft, powerGenerator);

		// Simple computer
		SystemComputer computer = new BasicSystemComputer("Simple System Computer", data.getBusComponentSpecification(), 1000 * Unit.GFLOP.value());
		SpacecraftBuildManager.addComponent(spacecraft, computer);
		
		for(SpacecraftBusComponent component : computer.getSpacecraftBus().getComponents()) {
			System.out.println(component.getName() + " " +  component.getNominalPower(Unit.MW) + " " + component.getNominalCPUThroughput(Unit.MFLOP));
		}
		
		
		System.out.println("No drive " + computer.getTotalCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW)
			+ " : "	+ computer.getCurrentCPUThroughput(Unit.MFLOP) + " " + computer.getTotalCPUThroughputAvailable(Unit.MFLOP));

		PropulsionManagementSoftware engineManagementSoftware = 
				new PropulsionManagementSoftware("Test EngineManagementSoftware");

		computer.loadSoftware(engineManagementSoftware);

		FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type(), false);
		SpacecraftBuildManager.addComponent(spacecraft, engine);

		double powerLevel = 34.45 * Unit.percent.value();
		SystemStatusMessage systemMsg4 = engineManagementSoftware.callDrive(powerLevel, engine.getIdent());
		assertEquals("Critical error status returned for drive", Status.SUCCESS, systemMsg4.getStatus());
		assertEquals("Engine power level incorrect", powerLevel, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Drive [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW));


		SystemStatusMessage systemMsg5 = engineManagementSoftware.callStop(engine.getIdent());
		assertEquals("Critical error status returned for stop", Status.SUCCESS, systemMsg5.getStatus());
		assertEquals("Engine power level incorrect", 0.0, engine.getPowerLevel(), 0.0001);
		
		System.out.println("Stop [" + engine.getPowerLevel() +  "] " + computer.getCurrentPower(Unit.MW) + " " + computer.getTotalPowerAvailable(Unit.MW));


		SystemStatusMessage systemMsg6 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine.getIdent());
		assertEquals("Critical error status returned for vector", Status.NOT_PERMITTED, systemMsg6.getStatus()); // passed false to engine factory

		FuelConsumingEngine engine2 = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type(), true);
		SpacecraftBuildManager.addComponent(spacecraft, engine2);

		SystemStatusMessage systemMsg7 = engineManagementSoftware.callVector(new EngineVector(new double[]{0,0,0}), engine2.getIdent());
		assertEquals("Critical error status returned for vector", Status.SUCCESS, systemMsg7.getStatus()); // passed true to engine factory



	}

}

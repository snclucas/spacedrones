package org.spacedrones.components.computers;

import org.junit.Before;
import org.junit.Test;
import org.spacedrones.components.comms.Status;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.*;
import org.spacedrones.status.SystemStatus;
import org.spacedrones.status.SystemStatusMessage;

import static org.junit.Assert.assertEquals;


public class SystemComputerTest {

	private double mass = 25.0 * Unit.kg.value();
	private double volume = 1.0 * Unit.m3.value();
	private double nominalPower = 1 * Unit.kW.value();
	private double nominalCPU = 10 * Unit.kFLOPs.value();
	private double maximumPower = 1 * Unit.MW.value();
	private double maximumCPU = nominalCPU;
	boolean vectored = false;

	private Bus spacecraftBus = null;


	private BusComponentSpecification busSpecs = new BusComponentSpecification(
			new PhysicalSpecification(mass, volume),
			new OperationalSpecification(nominalPower, nominalCPU, maximumPower, maximumCPU));


	@Before
	public void setUp() {
		double arrayArea = 1 * Unit.m.value() * 10 * Unit.m.value();
		double efficiency = 75 * Unit.percent.value();

		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs, 10 * Unit.GFLOPs.value());
    spacecraftBus = new SpacecraftBus(computer);

		BusComponentSpecification powerGeneratorBusSpecs = new BusComponentSpecification(
				new PhysicalSpecification(mass, volume),
				new OperationalSpecification(0, nominalCPU, 0, maximumCPU));

		PowerGenerator powerGenerator = new SubspacePowerExtractor("Test power generator", powerGeneratorBusSpecs, arrayArea, efficiency);
		spacecraftBus.register(powerGenerator);
	}

	@Test
	public void testRequestOperation() {
		SystemComputer computer = spacecraftBus.getSystemComputer();

		// Computer is not online so current power should be 0
		assertEquals("Computer current power should be (not online)", 0.0, computer.getCurrentPower(Unit.kW), 0.00001);
		SystemStatus systemStatus = computer.online();
		assertEquals("System computer should not have critical messages", false, systemStatus.hasCriticalMessages());
		assertEquals("System computer should have OK status", true, systemStatus.isOK());
		assertEquals("Computer current power should be " + busSpecs.getNominalPower(Unit.W) +
						" (online)", busSpecs.getNominalPower(Unit.W), computer.getCurrentPower(Unit.W), 0.00001);

		BusRequirement busRequirement = new BusRequirement(100 * Unit.W.value(), 100 * Unit.MFLOPs.value());
		SystemStatusMessage message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be permitted", Status.PERMITTED, message.getStatus());

		busRequirement = new BusRequirement(1100 * Unit.W.value(), 100 * Unit.MFLOPs.value());
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_POWER", Status.NOT_ENOUGH_POWER, message.getStatus());

		busRequirement = new BusRequirement(110 * Unit.W.value(), 10 * Unit.GFLOPs.value());
		message = computer.requestOperation(computer, busRequirement);
		assertEquals("Should be NOT_ENOUGH_GPU", Status.NOT_ENOUGH_CPU, message.getStatus());
	}



	@Test
	public void testSystemComputer() {
		SystemComputer computer = new BasicSystemComputer("Test computer", busSpecs,
				10 * Unit.GFLOPs.value());

    computer.getSoftware();

		assertEquals("Should be 0 computers", 0, computer.getComputers().size());
    assertEquals("Should be 0 engines", 0, computer.getEngines().size());
    assertEquals("Should be 0 communication devices", 0, computer.getCommunicationDevices().size());
    assertEquals("Should have 1 software (messaging software)", true, computer.hasSoftware());
    assertEquals("Should have 1 software (messaging software)", 1, computer.getSoftware().values().size());

    assertEquals("Should have 0 system messages", 0, computer.getSystemMessages().size());
    computer.addSystemMessage(null, "This is a message", Status.INFO);
    assertEquals("Should have 1 system messages", 1, computer.getSystemMessages().size());

    assertEquals("Should not be online", false, computer.isOnline());

    SystemStatus systemStatus = computer.online();



		computer.registerBus(spacecraftBus);
	}





}

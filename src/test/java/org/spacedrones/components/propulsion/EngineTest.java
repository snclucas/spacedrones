package org.spacedrones.components.propulsion;

import org.junit.Test;
import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.data.SpacecraftComponentData;
import org.spacedrones.physics.Unit;
import org.spacedrones.profiles.FuelConsumptionProfile;
import org.spacedrones.profiles.FuelConsumptionProfileFactory;
import org.spacedrones.profiles.ThrustProfile;
import org.spacedrones.profiles.ThrustProfileFactory;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;

import static org.junit.Assert.assertEquals;

public class EngineTest {

	//Align along axis of spacecraft
	EngineVector engineVector1 = new EngineVector(1,0,0);
	EngineVector engineVector2 = new EngineVector(1,1,1);



	double mass = 100 * Unit.kg.value();
	double volume = 1.0 * Unit.m3.value();
	double nominalPower = 1 * Unit.kW.value();
	double nominalCPU = 1 * Unit.kFLOPs.value();

	double maxPower = 1000 * Unit.kW.value();
	double maxCPU = 1 * Unit.kFLOPs.value();

	double maximumThrust = 1.04523 * Unit.kN.value(); // N


	@Test
	public void testEngineConstruction() {
		ThrustingEngine engine = getTestEngine(true);
		engine.callVector(new EngineVector(0.3,0.1, 0.5));

		assertEquals("Engine category incorrect", Engine.category, engine.category());
		assertEquals("Engine type ["+ engine.describe() +"] incorrect", SimpleThruster.type, engine.type());

		//Check the engine set up
		assertEquals("Engine power not set correctly", nominalPower, engine.getNominalPower(Unit.W), 0.001);
		assertEquals("Engine CPU not set correctly", nominalCPU, engine.getNominalCPUThroughput(Unit.MFLOPs), 0.001);
		assertEquals("Engine mass not set correctly", mass, engine.getMass(Unit.kg), 0.001);
		assertEquals("Engine volume not set correctly", volume, engine.getVolume(Unit.m3), 0.001);
		assertEquals("Engine power level not set correctly", 0.0, engine.getPowerLevel(), 0.001);
		assertEquals("Engine maximum thrust not set correctly", maximumThrust, engine.getMaximumThrust(), 0.001);
	}
	




	@Test
	public void testEngineVectoring() {
		// Engines can vector only if true is passed to their constructor.
		// Setting the engine vectoring (directly on the engine) will do nothing if the engine cannot be vectored.

		ThrustingEngine engine = getTestEngine(false);

		//Try to vector engine - should fail, and original engine vector in place.
		engine.callVector(engineVector2);
		assertEquals("Engine should not be able to be vectored", engineVector1, engine.getEngineVector());

		//New vectorable engine
		engine = getTestEngine(true);
		engine.callVector(engineVector2);
		engine.execute();

		//Try to vector engine - should work.
		assertEquals("Engine should be able to be vectored", engineVector2, engine.getEngineVector());

		//Test components
		assertEquals("Engine vectored incorrectly, component 1", 1, engine.getEngineVector().getVectorComponents()[0], 0.001);
		assertEquals("Engine vectored incorrectly, component 2", 1, engine.getEngineVector().getVectorComponents()[1], 0.001);
		assertEquals("Engine vectored incorrectly, component 3", 1, engine.getEngineVector().getVectorComponents()[2], 0.001);
		//Test object
		assertEquals("Engine vectored incorrectly", engineVector2, engine.getEngineVector());
	}




	@Test
	public void testEnginePowerLevels() {
		ThrustingEngine engine = EngineFactory.getEngine(SimpleIonEngine.type, true);
		/* Power level 0, expected zero thrust and nominal power use */
		double powerLevel = 0.0;
		
		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(Unit.W), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(Unit.W), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine required power incorrect", nominalPower, engine.getRequiredPower(powerLevel, Unit.W), 0.001);

		
		// Call drive with power level = 0;
		engine.callDrive(powerLevel);

		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(Unit.W), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(Unit.W), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine required power incorrect", nominalPower, engine.getRequiredPower(powerLevel, Unit.W), 0.001);

		// Call drive with power level = 50% WITHOUT calling execute;
		powerLevel = 50 * Unit.percent.value();

		engine.callDrive(powerLevel);

		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(Unit.W), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(Unit.W), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine required power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getRequiredPower(powerLevel, Unit.W), 0.001);

		//Call execute
		
		engine.execute();

		//Required and operating power should now be equal as the engine is operating at the set power level
		assertEquals("Engine nominal power incorrect", nominalPower, engine.getNominalPower(Unit.W), 0.001);
		assertEquals("Engine max power incorrect", maxPower, engine.getMaximumOperationalPower(Unit.W), 0.001);
		assertEquals("Engine operating power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine required power incorrect", nominalPower + (maxPower-nominalPower)*0.5, engine.getRequiredPower(powerLevel, Unit.W), 0.001);

	}


	@Test
	public void testEnginePowerAndThrust() {

		ThrustingEngine engine = getTestEngine(true);
		EngineVector engineVector = new EngineVector(0.3,0.1, 0.5);
		engine.callVector(engineVector);

		// Power level 0, expected zero thrust and nominal power use
		double powerLevel = 0.0;

		engine.callDrive(powerLevel);
		engine.execute();
		double[] velocity = new double[]{0.0, 0.0, 0.0};
		double[] thrust = engine.getThrust(velocity);

		// Simple linear model
		double expectedThrust = maximumThrust * powerLevel;
		double expectedPower = engine.getNominalPower(Unit.W) + powerLevel*maxPower;

		assertEquals("Engine power not set correctly", expectedPower, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine thrust not set correctly", expectedThrust, thrust[0], 0.001);

		powerLevel = 50.0 * Unit.percent.value(); 
		engine.callDrive(powerLevel); //50% power
		engine.execute();
		thrust = engine.getThrust(velocity);
		double[] expectedThrustVector = new double[]{
				engineVector.getVectorComponent(EngineVector.Axis.ROLL) * maximumThrust * powerLevel,
				engineVector.getVectorComponent(EngineVector.Axis.PITCH) * maximumThrust * powerLevel,
				engineVector.getVectorComponent(EngineVector.Axis.YAW) * maximumThrust * powerLevel
		};

		expectedPower = engine.getNominalPower(Unit.W) + (engine.getMaximumOperationalPower(Unit.W) - engine.getNominalPower(Unit.W)) * powerLevel;
		
		assertEquals("Engine power not set correctly", expectedPower, engine.getCurrentPower(Unit.W), 0.001);
		assertEquals("Engine thrust not set correctly ROLL_AXIS", expectedThrustVector[EngineVector.Axis.ROLL.getIndex()], 
				thrust[EngineVector.Axis.ROLL.getIndex()], 0.001);
		
		assertEquals("Engine thrust not set correctly PITCH_AXIS", expectedThrustVector[EngineVector.Axis.PITCH.getIndex()], 
				thrust[EngineVector.Axis.PITCH.getIndex()], 0.001);
		
		assertEquals("Engine thrust not set correctly YAW_AXIS", expectedThrustVector[EngineVector.Axis.YAW.getIndex()], 
				thrust[EngineVector.Axis.YAW.getIndex()], 0.001);

	}
	
	
	

	
	
	private ThrustingEngine getTestEngine(boolean vectored) {
		
		SpacecraftComponentData spacecraftComponentData = new SpacecraftComponentData(new BusComponentSpecification(
				new PhysicalSpecification(mass, volume), new OperationalSpecification(nominalPower, nominalCPU, maxPower, maxCPU)));

		ThrustProfile thrustProfile = ThrustProfileFactory.getThrustAlgorithm(
				ThrustProfileFactory.SIMPLE_LINEAR);	
		
		FuelConsumptionProfile fuelProfile = FuelConsumptionProfileFactory.getFuelConsumptionProfile (
				FuelConsumptionProfileFactory.SIMPLE_LINEAR);	

		//Align along axis of spacecraft
		EngineVector engineVector = new EngineVector(1,0,0);

		return new SimpleThruster(
				SimpleThruster.type.toString(), spacecraftComponentData.getBusComponentSpecification(),
				maximumThrust,
				thrustProfile, fuelProfile,  engineVector, vectored);
	}


}

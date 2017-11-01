package org.spacedrones.spacecraft;

import org.spacedrones.Configuration;
import org.spacedrones.components.comms.CommunicationComponent;
import org.spacedrones.components.comms.CommunicatorDeviceFactory;
import org.spacedrones.components.comms.RadioCommunicator;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.energygeneration.PowerGenerationFactory;
import org.spacedrones.components.energygeneration.PowerGenerator;
import org.spacedrones.components.energygeneration.SimpleSolarArray;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.EngineFactory;
import org.spacedrones.components.propulsion.thrust.FuelConsumingEngine;
import org.spacedrones.components.propulsion.thrust.FuelSubSystem;
import org.spacedrones.components.propulsion.thrust.FuelSubSystemFactory;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorFactory;
import org.spacedrones.components.sensors.SensorType;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.data.SpacecraftDataProvider;
import org.spacedrones.physics.Unit;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;
import org.spacedrones.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.spacedrones.structures.storage.fuel.FuelStorageTank;
import org.spacedrones.structures.storage.fuel.FuelStorageTankFactory;

import java.security.InvalidParameterException;

public class SpacecraftFactory {
	
	public final static String SHUTTLE="Shuttle"; 
	public final static String SIMPLE_SATELITE="Simple satelite";


	public static Spacecraft getSpacecraft(String spacecraftType) throws InvalidParameterException{
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

		Bus spacecraftBus = new SpacecraftBus();

		switch (spacecraftType) {
			
		case SHUTTLE:
			Hull hull = HullFactory.getHull(SHUTTLE);
			
			Spacecraft spacecraft = new SimpleSpacecraft(SHUTTLE, Configuration.getUUID(), hull, spacecraftBus);
			
			SystemComputer systemComputer = ComputerFactory.getComputer(BasicSystemComputer.type);
			SpacecraftBuildManager.addComponent(spacecraft, systemComputer);
			
			//PropulsionManagementSoftware engineManagementSoftware = new PropulsionManagementSoftware("Test EngineManagementSoftware", systemComputer);
			//systemComputer.loadSoftware(engineManagementSoftware);
			
			PowerGenerator powerGenerator = PowerGenerationFactory.getPowerGenerator(SubspacePowerExtractor.type);
			SpacecraftBuildManager.addComponent(spacecraft, powerGenerator);
			
			Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type, SensorType.RADAR, 1);
			SpacecraftBuildManager.addComponent(spacecraft, sensor);
			
			
			double tankCapacity = 100 * Unit.l.value();
			Fuel fuel = spacecraftDataProvider.getFuel(Fuel.HYDRAZINE);
			FuelStorageTank tank = FuelStorageTankFactory.getFuelStorageTank(CryogenicLiquidStorageTank.type, tankCapacity);
			tank.setFuel(fuel, tankCapacity);
			
			FuelSubSystem fuelDeliverySystem = FuelSubSystemFactory.getFuelSubsystem(
					FuelSubSystem.BASIC_FUEL_SUBSYSTEM, FuelSubSystem.PROPULSION_FUEL_SUBSYSTEM);
			fuelDeliverySystem.addFuelTank(tank);
			SpacecraftBuildManager.addComponent(spacecraft, fuelDeliverySystem);
			SpacecraftBuildManager.addComponent(spacecraft, tank);
			
			FuelConsumingEngine engine = (FuelConsumingEngine)EngineFactory.getEngine(SimpleThruster.type, false);
			engine.setFuelSubSystem(fuelDeliverySystem);
			SpacecraftBuildManager.addComponent(spacecraft, engine);
			
			CommunicationComponent commDevice = CommunicatorDeviceFactory.getCommunicator(RadioCommunicator.type);
			SpacecraftBuildManager.addComponent(spacecraft, commDevice);
			
			return spacecraft;
		case SIMPLE_SATELITE:
			Hull satHull = HullFactory.getHull("SimpleSatelite");
			
			Spacecraft sat = new SimpleSpacecraft("SimpleSatelite", Configuration.getUUID(), satHull, spacecraftBus);
			
			SystemComputer satSystemComputer = ComputerFactory.getComputer(BasicSystemComputer.type);
			SpacecraftBuildManager.addComponent(sat, satSystemComputer);
			
			PowerGenerator simpleSolarCell = PowerGenerationFactory.getPowerGenerator(SimpleSolarArray.type);
			SpacecraftBuildManager.addComponent(sat, simpleSolarCell);
			
			return sat;
		default:
			throw new InvalidParameterException("No such spacecraft.");
			
		}
		
		
	}

}

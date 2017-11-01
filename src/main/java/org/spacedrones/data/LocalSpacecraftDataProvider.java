package org.spacedrones.data;

import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.RadioCommunicator;
import org.spacedrones.components.comms.SubSpaceCommunicator;
import org.spacedrones.components.computers.BasicDataStorageUnit;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.energygeneration.SimpleSolarArray;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.sensors.FractalSensorArray;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.consumables.Fuel;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;
import org.spacedrones.structures.storage.fuel.CryogenicLiquidStorageTank;
import org.spacedrones.structures.storage.fuel.LiquidStorageTank;

public class LocalSpacecraftDataProvider implements SpacecraftDataProvider {

	@Override
	public SpacecraftComponentData getComponentParameters(TypeInfo componentType) {

		// mass volume power cpu 

		// computers 
		
		if(componentType.equals(BasicSystemComputer.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 20 * Unit.m3.value()), new OperationalSpecification(10 * Unit.kW.value(), 0 * Unit.MFLOP.value())));


		if(componentType.equals(LiquidStorageTank.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		if(componentType.equals(CryogenicLiquidStorageTank.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		// Datastore 
		
		if(componentType.equals(BasicDataStorageUnit.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		

		// Energy generators

		if(componentType.equals(SimpleSolarArray.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg.value(), 100 * Unit.m3.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));


		if(componentType.equals(SubspacePowerExtractor.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg.value(), 100 * Unit.m3.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		
		// Engines
		
		if(componentType.equals(SimpleIonEngine.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.0 * Unit.m3.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value(), 1000 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		if(componentType.equals(SimpleThruster.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.0 * Unit.m3.value()), new OperationalSpecification(100 * Unit.W.value(), 1 * Unit.kFLOP.value(), 1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));



		// Communication devices

		if(componentType.equals(RadioCommunicator.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		if(componentType.equals(SubSpaceCommunicator.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));


		//Sensors

		if(componentType.equals(LinearSensorArray.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

		if(componentType.equals(FractalSensorArray.type))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOP.value())));

	
		
		
		
		return null;
	}


	@Override
	public Fuel getFuel(int fuelType) {
		if(Fuel.LIQUID_HYDROGEN == fuelType)
			return new Fuel(70.8);
		else if(Fuel.LIQUID_OXYGEN == fuelType)
			return new Fuel(1141);
		else
			return new Fuel(1852.45);
	}




}

package org.spacedrones.data;

import org.spacedrones.components.comms.RadioCommunicator;
import org.spacedrones.components.comms.SubSpaceCommunicator;
import org.spacedrones.components.computers.BasicDataStorageUnit;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.energygeneration.SimpleSolarArray;
import org.spacedrones.components.energygeneration.SubspacePowerExtractor;
import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.components.propulsion.thrust.SimpleThruster;
import org.spacedrones.components.sensors.FractalSensorArray;
import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.sensors.StarTracker;
import org.spacedrones.consumables.Oxidizer;
import org.spacedrones.materials.Gas;
import org.spacedrones.materials.Liquid;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;
import org.spacedrones.structures.storage.propellant.GasStorageTank;
import org.spacedrones.structures.storage.propellant.LiquidStorageTank;


public class LocalSpacecraftDataProvider implements SpacecraftDataProvider {

	@Override
	public SpacecraftComponentData getComponentParameters(String componentType) {

		// mass volume power cpu

		// computers

		if(componentType.equals(Computer.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 20 * Unit.m3.value()), new OperationalSpecification(10 * Unit.kW.value(), 0 * Unit.MFLOPs.value())));

		if(componentType.equals(BasicSystemComputer.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
							new PhysicalSpecification(100 * Unit.kg.value(), 20 * Unit.m3.value()), new OperationalSpecification(10 * Unit.kW.value(), 0 * Unit.MFLOPs.value())));


		if(componentType.equals(LiquidStorageTank.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));

		if(componentType.equals(GasStorageTank.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));

		// Datastore

		if(componentType.equals(BasicDataStorageUnit.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));



		// Energy generators

		if(componentType.equals(SimpleSolarArray.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg.value(), 100 * Unit.m3.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));


		if(componentType.equals(SubspacePowerExtractor.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(1000 * Unit.kg.value(), 100 * Unit.m3.value()),
              new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));


		// Engines

		if(componentType.equals(SimpleIonEngine.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.0 * Unit.m3.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value(), 1000 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));

		if(componentType.equals(SimpleThruster.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.0 * Unit.m3.value()), new OperationalSpecification(100 * Unit.W.value(), 1 * Unit.kFLOPs.value(), 1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));



		// Communication devices

		if(componentType.equals(RadioCommunicator.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));

		if(componentType.equals(SubSpaceCommunicator.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));


		//Sensors

		if(componentType.equals(LinearSensorArray.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.1 * Unit.l.value()),
							new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));

		if(componentType.equals(FractalSensorArray.class.getSimpleName()))
			return new SpacecraftComponentData(new BusComponentSpecification(
					new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));


    if(componentType.equals(StarTracker.class.getSimpleName()))
      return new SpacecraftComponentData(new BusComponentSpecification(
              new PhysicalSpecification(100 * Unit.kg.value(), 1.3 * Unit.l.value()), new OperationalSpecification(1 * Unit.kW.value(), 1 * Unit.kFLOPs.value())));



		return null;
	}

	@Override
	public Liquid getLiquid(int liquidType) {
		if(Liquid.LIQUID_HYDROGEN == liquidType)
			return new Liquid("LH2", 1.32, 70.8, 2.016);
		else if(Oxidizer.LIQUID_OXYGEN == liquidType)
			return new Liquid("LO2", 1.35, 32.0, 1141);
		else if(Oxidizer.AIR == liquidType)
			return new Liquid("Air", 1.4, 29.0, 870);
    else if(Liquid.LIQUID_XENON == liquidType)
      return new Liquid("Liquid Xenon", 2942, 1.7, 131.293);
		else
			throw new IllegalArgumentException("No liquid found with that name");
	}

	@Override
	public Gas getGas(int gasType) {
		if(Gas.HYDROGEN == gasType)
			return new Gas("Hydrogen", 1.32, 70.8, 2.016);
		else
			throw new IllegalArgumentException("No liquid found with that name");
	}

}
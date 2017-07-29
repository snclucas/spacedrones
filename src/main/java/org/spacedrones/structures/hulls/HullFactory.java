package org.spacedrones.structures.hulls;

import org.spacedrones.Configuration;
import org.spacedrones.data.LocalMaterialDataProvider;
import org.spacedrones.materials.Material;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;

public class HullFactory {


	public static Hull getHull(String hullType){
		if(hullType.equalsIgnoreCase("Shuttle")){			
			double nominalPower = 1 * Unit.kW.value();
			double nominalCPUThroughput = 1 * Unit.kFLOP.value();

			double length = 10 * Unit.m.value();
			double width = 6 * Unit.m.value();
			double height = 6 * Unit.m.value();
			double volume  = length*width * Unit.m3.value();
			double thickness = 0.25 * Unit.m.value();
			Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.REINFORCED_TITANIUM);
			double mass = material.getDensity() * volume; // Not needed, calculated by Hull
			BusComponentSpecification busSpecs = new BusComponentSpecification(
					new PhysicalSpecification(mass, volume, length, width, height),
					new OperationalSpecification(nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));

			HullSpecification hullSpecification = new HullSpecification("SimpleMonocoqueHullSpec",
					busSpecs, thickness, material);

			return new SimpleMonocoqueHull("Shuttle", hullSpecification, Hull.SPHEROID);
		}
		if(hullType.equalsIgnoreCase("SimpleSatelite")){			
			double nominalPower = 0 * Unit.kW.value();
			double nominalCPUThroughput = 0 * Unit.kFLOP.value();

			double length = 10 * Unit.m.value();
			double width = 5 * Unit.m.value();
			double height = 5 * Unit.m.value();
			double volume  = length*width * Unit.m3.value();
			double thickness = 1.0 * Unit.cm.value();
			Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.REINFORCED_TITANIUM);
			double mass = material.getDensity() * volume; // Not needed, calculated by Hull
			BusComponentSpecification busSpecs = new BusComponentSpecification(
					new PhysicalSpecification(mass, volume, length, width, height),
					new OperationalSpecification(nominalPower, nominalCPUThroughput, nominalPower, nominalCPUThroughput));

			HullSpecification hullSpecification = new HullSpecification("SimpleMonocoqueHullSpec",
					busSpecs, thickness, material);

			return new SimpleMonocoqueHull("SimpleSatelite", hullSpecification, Hull.RECTANGULAR);
		}

		return null;
	}


}

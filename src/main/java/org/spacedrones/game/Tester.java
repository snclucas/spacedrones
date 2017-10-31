package org.spacedrones.game;

import org.spacedrones.Configuration;
import org.spacedrones.data.LocalMaterialDataProvider;
import org.spacedrones.materials.Material;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.spacecraft.OperationalSpecification;
import org.spacedrones.spacecraft.PhysicalSpecification;
import org.spacedrones.spacecraft.SimpleSpacecraft;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullSpecification;
import org.spacedrones.structures.hulls.SimpleMonocoqueHull;


public class Tester {
	
	
	public Tester() {
		Material material = Configuration.getMaterialDataProvider().getMaterial(LocalMaterialDataProvider.REINFORCED_TITANIUM);
		BusComponentSpecification busSpecs = new BusComponentSpecification(
				new PhysicalSpecification(1, 1, 100 * Unit.m.value(), 35 * Unit.m.value(), 35 * Unit.m.value()),
				new OperationalSpecification(0, 0, 0, 0));
		HullSpecification hullSpecification = new HullSpecification("SimpleMonocoqueHullSpec",
				busSpecs, 0.25 * Unit.m.value(), material);
		
		Hull hull = new SimpleMonocoqueHull("Simple hull", hullSpecification, Hull.Type.SPHEROID);
		
		Spacecraft spacecraft = new SimpleSpacecraft("", hull);
		

		
		
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Tester();
	}
	
	

}

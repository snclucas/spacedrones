package org.spacedrones.game;


import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.SimpleSpacecraft;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftBus;
import org.spacedrones.structures.hulls.Hull;
import org.spacedrones.structures.hulls.HullFactory;

public class Driver {
	
	
	public Driver() {

		Bus bus = new SpacecraftBus();

		Hull hull = HullFactory.getHull("Shuttle");

		Spacecraft spacecraft = new SimpleSpacecraft("Test", "1", hull, bus);

    spacecraft.online();



	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}

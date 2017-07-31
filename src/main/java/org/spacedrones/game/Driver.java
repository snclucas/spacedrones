package org.spacedrones.game;

import java.math.BigDecimal;
import java.util.List;

import org.spacedrones.components.sensors.LinearSensorArray;
import org.spacedrones.components.sensors.Sensor;
import org.spacedrones.components.sensors.SensorFactory;
import org.spacedrones.components.sensors.SensorResult;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;
import org.spacedrones.universe.SimpleLocation;
import org.spacedrones.universe.Universe;


public class Driver {
	
	
	public Driver() {
		Universe universe = Universe.getInstance();
		
		Location sol = new SimpleLocation("Sol", new Coordinates(
				new BigDecimal(8*Unit.kPc.value()),
				new BigDecimal(0),
				new BigDecimal(100*Unit.Ly.value())));
		
		Location initialSpacecraftLocation = new SimpleLocation("Spacecraft", new Coordinates(
				new BigDecimal(0),
				new BigDecimal(0),
				new BigDecimal(-10000*Unit.AU.value())), sol);
		

		System.out.println(sol);
		System.out.println(initialSpacecraftLocation);
		System.out.println(sol.vectorToLocation(initialSpacecraftLocation, true));
		System.out.println(sol.distanceToLocation(initialSpacecraftLocation, Unit.AU));
		System.out.println("----------------------------------------");
		
		
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		
		Coordinates coords = sol.getCoordinates().add(
				new Coordinates(new BigDecimal(10*Unit.AU.value()), BigDecimal.ZERO, BigDecimal.ZERO));
		universe.addSpacecraft(simpleSpacecraft, coords);
		
		universe.updateSpacecraftLocation(simpleSpacecraft.getIdent(), initialSpacecraftLocation);
		
		Sensor sensor = SensorFactory.getSensor(LinearSensorArray.type(), Sensor.RADAR, 1);
		simpleSpacecraft.addComponent(sensor);
		
		simpleSpacecraft.online();
		
		List<SensorResult> results = sensor.passiveScan(10, sensor.getSensorProfile());

		for(SensorResult result : results) {
			System.out.println(result.getNavigationVector());
		}
		
	
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}

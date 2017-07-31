package org.spacedrones.game;

import java.math.BigDecimal;

import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;
import org.spacedrones.universe.celestialobjects.SensorSignalResponseLibrary;
import org.spacedrones.universe.celestialobjects.Star;


public class SingleSpacecraftUniverse {

	public SingleSpacecraftUniverse() { 
		
		Universe universe = Universe.getInstance();
		Spacecraft spacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		Star sol = new Star("Sol", Star.G_CLASS_STAR, new Coordinates(new BigDecimal(8*Unit.kPc.value()),new BigDecimal(0),new BigDecimal(100*Unit.Ly.value())),
				SensorSignalResponseLibrary.getStandardSignalResponseProfile(Star.G_CLASS_STAR));
		universe.addSpacecraft(spacecraft, sol.getCoordinates());
		universe.updateSpacecraftLocation(spacecraft.getIdent(), 
				new Coordinates(new BigDecimal(8*Unit.kPc.value()), new BigDecimal(0.0), new BigDecimal(0.0)));
		
		
		

		run();	
	}

	private void run() {




	}

	public static void main(String[] args) {
		
		new SingleSpacecraftUniverse() ;
	}

}

package org.spacedrones.game;

import java.math.BigDecimal;

import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.spacecraft.SpacecraftFactory;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Universe;

public class GameRunner {


	private boolean gameRunning;
	private int lastFpsTime;
	private int fps;

	public GameRunner(Universe universe) {
		gameRunning = true;
		gameLoop(universe);
	}


	public void gameLoop(Universe universe)
	{
		 
		
		
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 10;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

		// keep looping round til the game ends
		while (gameRunning)
		{
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME); 

			// update the frame counter
			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000)
			{
				//System.out.println("(FPS: "+fps+")");
				lastFpsTime = 0;
				fps = 0;
			}

			// update the game logic
            //if(delta >-1)
			    //System.out.println(delta);

			// draw everything
            universe.tick();


			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give 
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in ns.

			long ss = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;

			try{
				Thread.sleep(ss);

			}
			catch(Exception e) {

			}



		}

	}




	public static void main(String[] args) {
		
		Universe universe = Universe.getInstance();
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		Coordinates coords = new Coordinates(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		universe.addSpacecraft(simpleSpacecraft, coords);



		Runner runner = new Runner();
		runner.addManager(new SpacecraftMotionManager(universe));
		
		new GameRunner(universe);
	}

}

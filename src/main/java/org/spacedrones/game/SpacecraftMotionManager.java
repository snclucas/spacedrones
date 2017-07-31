package org.spacedrones.game;


import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.Location;
import org.spacedrones.universe.Universe;

import java.math.BigDecimal;
import java.util.Map;

public class SpacecraftMotionManager {

    private Universe universe;


    public SpacecraftMotionManager(Universe universe) {
        this.universe = universe;
    }


    public void moveSpacecraft(double dt) {
        for (Map.Entry<String, Spacecraft> spacecraft : universe.getSpacecraft().entrySet()) {
            Spacecraft sc = spacecraft.getValue();
            double[] velocity = universe.getSpacecraftVelocity(sc.getIdent());
            Coordinates scLocation = universe.getSpacecraftLocation(sc.getIdent());

            double[] distance = distanceTraveledInDt(velocity, dt);

            scLocation.addDistance(new BigDecimal[]{new BigDecimal(distance[0]), new BigDecimal(distance[1]), new BigDecimal(distance[2])});
        }
    }


    private double[] distanceTraveledInDt(double[] velocity, double dt) {
        double[] distance = new double[]{
          velocity[0] * dt,
          velocity[0] * dt,
          velocity[0] * dt
        };
        return distance;
    }


}

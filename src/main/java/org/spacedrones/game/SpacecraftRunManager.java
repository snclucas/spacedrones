package org.spacedrones.game;


import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.*;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.dataprovider.ObjectLocationDataProvider;

import java.math.BigDecimal;
import java.util.List;

public final class SpacecraftRunManager implements SpacecraftManager, RunManager {

  private ObjectLocationDataProvider spacecraftDataProvider;

  private Bus bus;

  SpacecraftRunManager(ObjectLocationDataProvider spacecraftDataProvider) {
    this.spacecraftDataProvider = spacecraftDataProvider;

  }

  public void receiveManagerHandle(AbstractSpacecraft.Handle handle) {
    this.bus = handle.getBus();
  }

  public void setSpacecraftVelocity(String ident, double[] velocity) {
    spacecraftDataProvider.updateSpacecraftVelocity(ident, velocity);
  }

  private void moveAllSpacecraft(double dt) {
    for (Spacecraft spacecraft : spacecraftDataProvider.getAllSpacecraft()) {
      double[] velocity = spacecraftDataProvider.getSpacecraftVelocity(spacecraft.id());
      Coordinates scLocation = spacecraftDataProvider.getSpacecraftLocation(spacecraft.id());
      double[] distance = distanceTraveledInDt(velocity, dt);
      scLocation.addDistance(new BigDecimal[]{new BigDecimal(distance[0]), new BigDecimal(distance[1]), new BigDecimal(distance[2])});
    }

    //Move the spacecraft
    for (Spacecraft spacecraft : spacecraftDataProvider.getAllSpacecraft()) {
      spacecraft.tick(dt);
      Coordinates currentLocation = spacecraftDataProvider.getSpacecraftLocation(spacecraft.id());
      double[] currentVelocity = spacecraftDataProvider.getSpacecraftVelocity(spacecraft.id());

      //System.out.println("Current velocity: " + currentVelocity[0] + " " + currentVelocity[1] + " " + currentVelocity[2]);

      spacecraft.giveManagerHandleTo(this);

      double[] dV = new double[]{0.0, 0.0, 0.0};
      List<SpacecraftBusComponent> components = bus.findComponentByType(ThrustingEngine.class);
      for (SpacecraftBusComponent component : components) {
        double[] thrust = ((ThrustingEngine) component).getThrust(currentVelocity);
        dV[0] += thrust[0] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
        dV[1] += thrust[1] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
        dV[2] += thrust[2] / spacecraft.getMass(Unit.kg) * 1 * Unit.s.value();
      }
      double[] newVelocity = new double[]{
              currentVelocity[0] + dV[0], currentVelocity[1] + dV[1], currentVelocity[2] + dV[2]
      };

      //System.out.println("New velocity: " + newVelocity[0] + " " + newVelocity[1] + " " + newVelocity[2]);

      double[] translation = new double[]{
              currentVelocity[0] * dt * Unit.s.value(), currentVelocity[1] * dt * Unit.s.value(), currentVelocity[2] * dt * Unit.s.value()
      };

      //System.out.println("Translation: " + translation[0] + " " + translation[1] + " " + translation[2]);

      currentLocation = currentLocation.addDistance(new BigDecimal[]{
              new BigDecimal(translation[0]),
              new BigDecimal(translation[1]),
              new BigDecimal(translation[2])
      });

      spacecraftDataProvider.updateSpacecraftLocation(spacecraft.id(), currentLocation);
      spacecraftDataProvider.updateSpacecraftVelocity(spacecraft.id(), newVelocity);


      System.out.println("Current location: " + currentLocation);
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

  @Override
  public void tick(double dt) {
    moveAllSpacecraft(dt);
  }
}

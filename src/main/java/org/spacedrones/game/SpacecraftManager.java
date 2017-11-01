package org.spacedrones.game;


import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.propulsion.thrust.ThrustingEngine;
import org.spacedrones.physics.Unit;
import org.spacedrones.spacecraft.AbstractSpacecraft;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.Spacecraft;
import org.spacedrones.universe.Coordinates;
import org.spacedrones.universe.dataprovider.SpacecraftDataProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class SpacecraftManager implements Manager {

  private SpacecraftDataProvider spacecraftDataProvider;

  private Bus bus;

  public SpacecraftManager(SpacecraftDataProvider spacecraftDataProvider) {
    this.spacecraftDataProvider = spacecraftDataProvider;

  }

  public void receiveHandle(AbstractSpacecraft.Handle handle) {
    this.bus = handle.getBus();
  }

  public void setSpacecraftVelocity(String ident, double[] velocity) {
    spacecraftDataProvider.updateSpacecraftVelocity(ident, velocity);
  }

  private void moveAllSpacecraft(double dt) {
    for (Map.Entry<String, Spacecraft> spacecraft : spacecraftDataProvider.getAllSpacecraft().entrySet()) {
      Spacecraft sc = spacecraft.getValue();
      double[] velocity = spacecraftDataProvider.getSpacecraftVelocity(sc.getId());
      Coordinates scLocation = spacecraftDataProvider.getSpacecraftLocation(sc.getId());
      double[] distance = distanceTraveledInDt(velocity, dt);
      scLocation.addDistance(new BigDecimal[]{new BigDecimal(distance[0]), new BigDecimal(distance[1]), new BigDecimal(distance[2])});
    }

    //Move the spacecraft
    for (Spacecraft spacecraft : spacecraftDataProvider.getAllSpacecraft().values()) {
      spacecraft.tick();
      Coordinates currentLocation = spacecraftDataProvider.getSpacecraftLocation(spacecraft.getId());
      double[] currentVelocity = spacecraftDataProvider.getSpacecraftVelocity(spacecraft.getId());

      //System.out.println("Current velocity: " + currentVelocity[0] + " " + currentVelocity[1] + " " + currentVelocity[2]);

      spacecraft.giveHandleTo(this);

      double[] dV = new double[]{0.0, 0.0, 0.0};
      List<SpacecraftBusComponent> components = bus.findComponentByType(ThrustingEngine.type);
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

      spacecraftDataProvider.updateSpacecraftLocation(spacecraft.getId(), currentLocation);
      spacecraftDataProvider.updateSpacecraftVelocity(spacecraft.getId(), newVelocity);


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

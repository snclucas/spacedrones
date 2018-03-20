package org.spacedrones.software;


import org.spacedrones.components.sensors.StarTracker;

import java.util.Optional;

public class AttitudeSoftware extends AbstractSoftware implements Software {


  AttitudeSoftware(final String name) {
    super(name);
  }

  public void init() {
    Optional<StarTracker> starTracker = getSystemComputer().findComponentByType(StarTracker.class).stream().findFirst();



  }

  public void getAttitude() {

  }


  @Override
  public String getDescription() {
    return "Attitude control";
  }
}

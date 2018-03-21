package org.spacedrones.software;


import org.spacedrones.components.computers.*;
import org.spacedrones.components.sensors.*;
import org.spacedrones.physics.*;

import java.util.*;

public class AttitudeSoftware extends AbstractSoftware implements Software {

  private StarTracker starTracker;

  AttitudeSoftware(final String name) {
    super(name);
  }

  public boolean init() {
    Optional<StarTracker> starTrackerOptional = getSystemComputer().orElseThrow(() ->
            new NoSuchElementException(this.getDescription() + ": No system computer"))
            .findComponentByType(StarTracker.class).stream().findFirst();
    if(starTrackerOptional.isPresent()) {
      starTracker = starTrackerOptional.get();
      return starTracker.isOnline();
    }
    return false;
  }

  public void getAttitude() {
    StdAppMagnitude stdAppMagnitudes = StdAppMagnitude.V;
    EMSensorProfile sensorProfile = new EMSensorProfile(StdAppMagnitude.V,
            EMSensorThreshold.asMagnitude(16, stdAppMagnitudes), 10);


    List<SensorResult> starsFromScan = starTracker.passiveScan(1, sensorProfile);

    System.out.println(starsFromScan.size());


  }


  @Override
  public String getDescription() {
    return "Attitude control";
  }

}

package org.spacedrones.game;


import org.spacedrones.spacecraft.RunManager;

import java.util.ArrayList;
import java.util.List;

public class Runner {

  List<RunManager> managers = new ArrayList<>();

  public void addManager(RunManager manager) {
    managers.add(manager);
  }

  public void tick(double dt) {
    managers.forEach(m -> m.tick(dt));
  }

}

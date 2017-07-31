package org.spacedrones.game;


import java.util.ArrayList;
import java.util.List;

public class Runner {

  List<Manager> managers = new ArrayList<>();

  public void addManager(Manager manager) {
    managers.add(manager);
  }

  public void run(double dt) {
    managers.forEach(m -> m.run(dt));
  }

}

package org.spacedrones.game;


import org.spacedrones.spacecraft.AbstractSpacecraft;

public interface Manager {

  void receiveHandle(AbstractSpacecraft.Handle handle);

  void tick(double dt);

}

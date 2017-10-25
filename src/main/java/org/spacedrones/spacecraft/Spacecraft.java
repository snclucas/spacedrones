package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.structures.hulls.Hull;


public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable, Tickable, Identifiable {

  double[] getVelocity();

	double getMass();

	Hull getHull();

}

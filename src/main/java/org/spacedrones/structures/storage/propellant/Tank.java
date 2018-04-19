package org.spacedrones.structures.storage.propellant;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.materials.*;

public interface Tank extends SpacecraftBusComponent {

	double getCapacity();

	double getLevel();

	void setContents(Fluid contents);

	void fill(double volume);

	void empty(double volume);

  Fluid getContents();

	double getVolumeLevel();

}
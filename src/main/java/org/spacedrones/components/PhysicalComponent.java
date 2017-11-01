package org.spacedrones.components;

import org.spacedrones.physics.Unit;

public interface PhysicalComponent extends Taxonomic {
	double getMass(Unit unit);
	double getVolume(Unit unit);
}

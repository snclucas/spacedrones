package org.spacedrones.components;

import org.spacedrones.physics.Unit;

public interface PhysicalComponent {
	double getMass();
	double getVolume();
	double getMass(Unit unit);
	double getVolume(Unit unit);
	void setMass(double mass);
	void setVolume(double volume);
}

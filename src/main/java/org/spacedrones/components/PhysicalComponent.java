package org.spacedrones.components;

import org.spacedrones.physics.Unit;

public interface PhysicalComponent extends Component {
	double getMass(Unit unit);
	double getVolume(Unit unit);
}

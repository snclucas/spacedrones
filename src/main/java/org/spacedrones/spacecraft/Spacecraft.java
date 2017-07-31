package org.spacedrones.spacecraft;

import java.util.List;

import org.spacedrones.components.*;
import org.spacedrones.physics.Unit;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.structures.hulls.Hull;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable, Tickable, Identifiable {
	
	TypeInfo category = new TypeInfo("Spacecraft");
	
	double getVolume(Unit unit);
	double getMass(Unit unit);
	double getLength(Unit unit);
	double getWidth(Unit unit);
	
	double getTotalMassOfComponents();
	double getTotalVolumeOfComponents();
	
	void addComponent(SpacecraftBusComponent component);
	List<SpacecraftBusComponent> getComponents();
	
	Hull getHull();
	
	Bus getSpacecraftBus();
}

package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.structures.hulls.Hull;

import java.util.List;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable, Tickable, Identifiable {
	
	TypeInfo category = new TypeInfo("Spacecraft");
	
	double getTotalMassOfComponents();
	double getTotalVolumeOfComponents();
	
	//void addComponent(SpacecraftBusComponent component);
	List<SpacecraftBusComponent> getComponents();
	
	Hull getHull();
	
	Bus getSpacecraftBus();
}

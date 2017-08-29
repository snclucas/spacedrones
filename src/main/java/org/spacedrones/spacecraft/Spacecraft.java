package org.spacedrones.spacecraft;

import org.spacedrones.components.*;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.structures.hulls.Hull;

import java.util.List;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable, Tickable, Identifiable {
	
	double getTotalMassOfComponents();
	double getTotalVolumeOfComponents();

	List<SpacecraftBusComponent> getComponents();
	
	Hull getHull();
	
	//Bus getSpacecraftBus();

}

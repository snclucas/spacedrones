package org.spacedrones.spacecraft;

import java.util.List;

import org.spacedrones.components.Component;
import org.spacedrones.components.Diagnosable;
import org.spacedrones.components.Onlineable;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.Tickable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.status.StatusProvider;
import org.spacedrones.structures.hulls.Hull;

public interface Spacecraft extends StatusProvider, Onlineable, Diagnosable, Tickable {
	
	TypeInfo category = new TypeInfo("Spacecraft");
	
	String getName();
	String getIdent();
	
	double getVolume();
	double getMass();
	double getLength();
	double getWidth();
	
	double getTotalMassOfComponents();
	double getTotalVolumeOfComponents();
	
	void addComponent(Component component);
	List<SpacecraftBusComponent> getComponents();
	
	Hull getHull();
	
	Bus getSpacecraftBus();
	void setSpacecraftBus(Bus bus);
}

package org.spacedrones.components.computers;

import java.util.Map;

import org.spacedrones.components.AbstractBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;
import org.spacedrones.software.Software;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.BusComponentSpecification;
import org.spacedrones.status.SystemStatusMessage;

public abstract class AbstractComputer extends AbstractBusComponent implements Computer {
	
	//public static TypeInfo category() {
	//	return new TypeInfo("Computer");
	//}
	
	//public static TypeInfo type() {
	//	return new TypeInfo("Computer");
	//}

	
	protected Map<TypeInfo, Software> loadedSoftware;
	
	public AbstractComputer(String name, BusComponentSpecification busResourceSpecification) {
		super(name, busResourceSpecification);
	}
	
	public SystemComputer getSystemComputer() {
		return getSpacecraftBus().getSystemComputer();
	}

	@Override
	public SystemStatusMessage loadSoftware(Software software) {
		software.setComputer(this);
		if(loadedSoftware.put(software.getType(), software) != null)
			return new SystemStatusMessage(this, software.getDescription() + " software loaded", getUniversalTime(), Status.OK);
		else 
			return new SystemStatusMessage(this, software.getDescription() + " software replaced exisiting software", getUniversalTime(), Status.OK);
	}

	
	@Override
	public Software getSoftware(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType);
	}

	
	@Override
	public boolean hasSoftware(TypeInfo softwareType) {
		return loadedSoftware.get(softwareType) != null;
	}

	
	@Override
	public Bus getSpacecraftBus() {
		return spacecraftBus;
	}


	
	

	@Override
	public double getMaxCPUThroughput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerSpacecraftBus(Bus bus) {
		// TODO Auto-generated method stub
		
	}

	
	
	public static TypeInfo category() {
		return new TypeInfo("Computer");
	}
	
	public static TypeInfo type() {
		return new TypeInfo("Computer");
	}
	
	
	public TypeInfo getType() {
		return type();
	}

	public TypeInfo getCategory() {
		return category();
	}
	
	
	@Override
	public void tick() {
	}
	
	

}

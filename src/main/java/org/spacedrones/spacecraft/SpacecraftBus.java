package org.spacedrones.spacecraft;

import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.software.Message;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.List;

public class SpacecraftBus implements Bus {

	private final List<SpacecraftBusComponent> components = new ArrayList<>();


	@Override
	public Message recieveBusMessage(Message message) {
		return null;
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByType(TypeInfo componentType) {
		return SpacecraftFirmware.findBusComponentByType(this, componentType);
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByCategory(TypeInfo componentCategory) {
		return SpacecraftFirmware.findBusComponentByCategory(this, componentCategory);
	}

	@Override
	public List<SpacecraftBusComponent> getComponents() {
		return components;
	}

	@Override
	public SystemStatusMessage registerSystemComputer(SystemComputer computer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(SpacecraftBusComponent component) {
		if(component instanceof SystemComputer)
			findComponentByType(SystemComputer.type()).clear();
		this.components.add(component);
		component.registerBus(this);
	}

	@Override
	public SystemComputer getSystemComputer() {
		List<SpacecraftBusComponent> components = findComponentByType(SystemComputer.type());
		if(components.size() == 0)
			return null;
		return (SystemComputer)components.get(0);
	}

}

package org.spacedrones.spacecraft;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.software.Message;
import org.spacedrones.status.SystemStatusMessage;

import java.util.ArrayList;
import java.util.List;

public class SpacecraftBus implements Bus {

  private final String name;
  private final String id;
	private final List<SpacecraftBusComponent> components = new ArrayList<>();

	public SpacecraftBus() {
    this.name = "SpacecraftBus";
    this.id = Configuration.getUUID();
  }

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

	public void register(SpacecraftBusComponent component) {
		if(component instanceof SystemComputer)
			findComponentByType(SystemComputer.type).clear();
		this.components.add(component);
		component.registerBus(this);
	}

	public SystemComputer getSystemComputer() {
		List<SpacecraftBusComponent> components = findComponentByCategory(SystemComputer.type);
		if(components.size() == 0) {
      return null;
    }
		return (SystemComputer)components.get(0);
	}

  public String getName() {
	  return name;
  }

  public String getId(){
    return id;
  }

  public String describe(){
    return "SpacecraftBus";
  }

	@Override
	public TypeInfo category() {
		return category;
	}

	@Override
	public TypeInfo type() {
		return type;
	}

}

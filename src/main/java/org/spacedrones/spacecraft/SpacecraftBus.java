package org.spacedrones.spacecraft;

import org.spacedrones.Configuration;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.software.Message;

import java.util.ArrayList;
import java.util.List;

public class SpacecraftBus implements Bus {

  private final String name;
  private final String id;
	private final List<SpacecraftBusComponent> components = new ArrayList<>();
	private SystemComputer systemComputer;

	public SpacecraftBus() {
    this.name = "SpacecraftBus";
    this.id = Configuration.getUUID();
  }

  public SpacecraftBus(SystemComputer systemComputer) {
    this.name = "SpacecraftBus";
    this.id = Configuration.getUUID();
    register(systemComputer);
  }

	@Override
	public Message recieveBusMessage(Message message) {
		return null;
	}

	@Override
	public List<SpacecraftBusComponent> findComponentByType(Class<? extends SpacecraftBusComponent> component) {
		return SpacecraftFirmware.findBusComponentByType(this, component);
	}

	@Override
	public List<SpacecraftBusComponent> getComponents() {
		return components;
	}

	public void register(SpacecraftBusComponent component) {
		if(component instanceof SystemComputer) {
      findComponentByType(SystemComputer.class).clear();
      ((SystemComputer)component).registerBus(this);
    }
		this.components.add(component);
	}

	public SystemComputer getSystemComputer() {
		List<SpacecraftBusComponent> components = findComponentByType(SystemComputer.class);
		if(components.size() == 0) {
      return null;
    }
		return (SystemComputer)components.get(0);
	}

  public String name() {
	  return name;
  }

  public String id(){
    return id;
  }

  public String description(){
    return "SpacecraftBus";
  }

}

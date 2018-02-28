package org.spacedrones.software;

import org.junit.Test;
import org.spacedrones.components.SpacecraftBusComponent;
import org.spacedrones.components.computers.BasicSystemComputer;
import org.spacedrones.components.computers.ComputerFactory;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.components.propulsion.Engine;
import org.spacedrones.spacecraft.Bus;
import org.spacedrones.spacecraft.SpacecraftBus;
import org.spacedrones.status.SystemStatus;

import java.util.List;
import java.util.Map;

public class SystemMessageTest {


	@Test
	public void testEngineManagementSoftwareNoEngine() {

		SystemComputer systemComputer = ComputerFactory.getSystemComputer(BasicSystemComputer.class.getSimpleName());
		Bus bus = new SpacecraftBus(systemComputer);

		//Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		SystemStatus status = systemComputer.online();


		if(!status.isOK()) {
			System.out.println("There was a problem onlining your system computer");
			System.exit(-1);
		}


		Message message = new SystemMessage(null, null,"{cmd:callVector,params:[1,0,0]}");
		Map<String, Message> messages = systemComputer.getMessagingSystem().broadcastMessage(message);

		for(Message msg : messages.values()) {
			System.out.println(msg.getMessage());
		}

		List<SpacecraftBusComponent> components = systemComputer.findComponentByType(Engine.class);

		Message targetedMessage = new SystemMessage(null, null,"Test targeted message");
		Message mess = systemComputer.getMessagingSystem().sendMessageTo(targetedMessage, components.get(0));

		System.out.println(mess.getMessage());

	}

}


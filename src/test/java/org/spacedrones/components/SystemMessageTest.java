package org.spacedrones.components;

import org.junit.Test;
import org.spacedrones.components.comms.RadioCommunicator;
import org.spacedrones.components.propulsion.thrust.SimpleIonEngine;
import org.spacedrones.software.SystemMessage;

import static org.junit.Assert.assertEquals;

public class SystemMessageTest {

	@Test
	public void testSystemMessage() {
		
		MockIdentifiableObject reciever = new MockIdentifiableObject("12", "Test reciever", RadioCommunicator.category, RadioCommunicator.type);
		MockIdentifiableObject sender = new MockIdentifiableObject("123", "Test sender", SimpleIonEngine.category, SimpleIonEngine.type);
	
		SystemMessage systemMessage = new SystemMessage(reciever, sender, "This is a test message", 1273);
		
		assertEquals("Reciever ID incorrect", reciever.getId(), systemMessage.getRecieverId());
		assertEquals("Sender ID incorrect", sender.getId(), systemMessage.getSenderId());
		assertEquals("Message incorrect", "This is a test message", systemMessage.getMessage());
		assertEquals("Universal date incorrect", 1273, systemMessage.getUniversalDate(), 0.001);
	}

}

class MockIdentifiableObject implements Identifiable {
	
	String ident;
	String name;
	TypeInfo category;
	TypeInfo type;
	
	
	MockIdentifiableObject(String ident, String name, TypeInfo category, TypeInfo type) {
		super();
		this.ident = ident;
		this.name = name;
		this.category = category;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public String getId() {
		return ident;
	}


	@Override
	public String describe() {
		return "Mock";
	}
	
}

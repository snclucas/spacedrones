package org.spacedrones.status;

import org.junit.Test;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.comms.Status;

import static org.junit.Assert.assertEquals;

public class SystemStatusTest {

	@Test
	public void testSystemStatus() {
		SystemStatus systemStatus = new SystemStatus(new MockStatusProvider());

		systemStatus.addSystemMessage("Test system message OK", 1, Status.OK);
		assertEquals("Should have OK status", true, systemStatus.isOK());

		systemStatus.addSystemMessage("Test system message WARNING", 1, Status.WARNING);
		assertEquals("Should have warning status", true, systemStatus.hasWarningMessages());


		systemStatus.addSystemMessage("Test system message CRITICAL", 1, Status.CRITICAL);
		assertEquals("Should have critical status", true, systemStatus.hasCriticalMessages());

		assertEquals("Number of messages incorrect", 3, systemStatus.getNumberOfMessages());

		SystemStatusMessage msg = new SystemStatusMessage(new MockStatusProvider(), "Test system message OK", 1, Status.OK);
		systemStatus.addSystemMessage(msg);

		assertEquals("Number of messages incorrect", 4, systemStatus.getNumberOfMessages());
		
		//To keep code coverage happy
		systemStatus.toString();
	}


	@Test
	public void testMergeSystemStatus() {
		SystemStatus systemStatus1 = new SystemStatus(new MockStatusProvider());
		systemStatus1.addSystemMessage("Test system message OK", 1, Status.OK);

		SystemStatus systemStatus2 = new SystemStatus(new MockStatusProvider());
		systemStatus2.addSystemMessage("Test system message OK", 1, Status.OK);
		
		systemStatus2.mergeSystemStatus(systemStatus1);

		assertEquals("Number of messages incorrect", 2, systemStatus2.getNumberOfMessages());
	}

	
	@Test
	public void testMergeSystemStatus2() {
		SystemStatus systemStatus1 = new SystemStatus(new MockStatusProvider());
		systemStatus1.addSystemMessage("Test system message OK", 1, Status.OK);

		SystemStatus systemStatus2 = new SystemStatus(new MockStatusProvider());
		systemStatus2.addSystemMessage("Test system message OK", 1, Status.OK);
		
		systemStatus2.mergeSystemMessages(systemStatus1.getMessages());

		assertEquals("Number of messages incorrect", 2, systemStatus2.getNumberOfMessages());
	}

}

class MockStatusProvider implements StatusProvider{

	@Override
	public TypeInfo getType() {
		return new TypeInfo("MockStatusProvider");
	}

	@Override
	public TypeInfo getCategory() {
		return new TypeInfo("MockStatusProviderCategory");
	}

	@Override
	public String getName() {
		return "Mock Status Provider";
	}

	@Override
	public String getIdent() {
		return "100";
	}

	@Override
	public String describe() {
		return"MockStatusProvider";
	}

}

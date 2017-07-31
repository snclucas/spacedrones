package org.spacedrones.software;

import org.spacedrones.components.Identifiable;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.StatusProvider;

public interface Software extends StatusProvider, Identifiable {
	
	TypeInfo categoryID = new TypeInfo("Software");
	
	void setComputer(Computer computer);
	
	String getDescription();
	
	SystemComputer getSystemComputer();
}

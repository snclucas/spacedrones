package org.spacedrones.software;

import org.spacedrones.components.Component;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.StatusProvider;

public interface Software extends Component, StatusProvider {
	TypeInfo category = new TypeInfo("Software");
	TypeInfo type = category;

	void setComputer(Computer computer);
	String getDescription();
	SystemComputer getSystemComputer();

}

package org.spacedrones.software;

import org.spacedrones.components.Taxonomic;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.StatusProvider;

public interface Software extends Taxonomic, StatusProvider {
	TypeInfo category = new TypeInfo("Software");
	TypeInfo type = category;

	void setComputer(Computer computer);
	String getDescription();
	SystemComputer getSystemComputer();

}

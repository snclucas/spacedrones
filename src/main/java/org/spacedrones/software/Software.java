package org.spacedrones.software;

import org.spacedrones.*;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.StatusProvider;

public interface Software extends Taxonomic, StatusProvider {
	void setComputer(Computer computer);
	String getDescription();
	SystemComputer getSystemComputer();

}

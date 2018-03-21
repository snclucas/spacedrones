package org.spacedrones.software;

import org.spacedrones.components.*;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;
import org.spacedrones.status.StatusProvider;

import java.util.*;

public interface Software extends Taxonomic, StatusProvider {
	void setComputer(Computer computer);
	String getDescription();
	Optional<SystemComputer> getSystemComputer();

}

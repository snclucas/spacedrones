package org.spacedrones.software;

import org.spacedrones.Configuration;
import org.spacedrones.components.TypeInfo;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	private Computer computer;
	protected final String ident;
	
	public AbstractSoftware(String name) {
		this.name = name;
		this.ident = Configuration.getUUID();
	}

	
	@Override
	public final TypeInfo getCategory() {
		return categoryID;
	}
	
	@Override
	public String getIdent() {
		return ident;
	}
	
	@Override
	public void setComputer(Computer computer) {
		this.computer = computer;
	}


	@Override
	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public SystemComputer getSystemComputer() {
		return computer.getSystemComputer();
	}
	
	

}

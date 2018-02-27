package org.spacedrones.software;

import org.spacedrones.Configuration;
import org.spacedrones.components.computers.Computer;
import org.spacedrones.components.computers.SystemComputer;

public abstract class AbstractSoftware implements Software {
	
	private String name;
	
	private Computer computer;
	protected final String ident;
	
	AbstractSoftware(String name) {
		this.name = name;
		this.ident = Configuration.getUUID();
	}

	@Override
	public String id() {
		return ident;
	}
	
	@Override
	public void setComputer(Computer computer) {
		this.computer = computer;
	}


	@Override
	public String name() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public SystemComputer getSystemComputer() {
		return computer.getSystemComputer();
	}

}

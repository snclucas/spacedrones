package org.spacedrones.profiles;

import org.spacedrones.Configuration;
import org.spacedrones.components.Identifiable;

public abstract class AbstractProfile implements Identifiable {

	private String name;
	private final String ident;
	
	public AbstractProfile(String name) {
		this.name = name;
		this.ident = Configuration.getUUID();
	}

	public String name() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String id() {
		return this.ident;
	}
	
}

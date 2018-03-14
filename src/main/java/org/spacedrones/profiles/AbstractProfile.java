package org.spacedrones.profiles;

import org.spacedrones.Configuration;
import org.spacedrones.components.Taxonomic;

public abstract class AbstractProfile implements Taxonomic {

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

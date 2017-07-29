package org.spacedrones.algorithm;

public abstract class AbstractModel implements Model {

	protected String name;
	
	public AbstractModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}

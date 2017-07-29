package org.spacedrones.exceptions;

public class ComponentConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComponentConfigurationException(String meessage) {
		super(meessage);
	}

}

package org.spacedrones.exceptions;

public class SpacecraftNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SpacecraftNotFoundException(String message) {
		super(message);
	}

}

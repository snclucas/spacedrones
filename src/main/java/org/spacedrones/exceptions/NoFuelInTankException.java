package org.spacedrones.exceptions;

public class NoFuelInTankException extends RuntimeException {

	private static final long serialVersionUID = -700448799990176916L;

	public NoFuelInTankException(String message) {
		super(message);
	}
	
	

}

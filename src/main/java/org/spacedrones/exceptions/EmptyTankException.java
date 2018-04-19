package org.spacedrones.exceptions;

public class EmptyTankException extends RuntimeException {

	private static final long serialVersionUID = -700448799990176916L;

	public EmptyTankException(String message) {
		super(message);
	}
	
	

}

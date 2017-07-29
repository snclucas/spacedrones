package org.spacedrones.exceptions;

public class IncorrectUnitException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IncorrectUnitException(String message) {
		super(message);
	}

}

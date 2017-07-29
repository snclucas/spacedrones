package org.spacedrones.exceptions;

public class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotImplementedException() {
		super("The typeinfor method is not implmented for this class.");
	}

}

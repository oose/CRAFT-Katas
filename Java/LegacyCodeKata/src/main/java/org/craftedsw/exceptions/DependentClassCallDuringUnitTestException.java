package org.craftedsw.exceptions;

public class DependentClassCallDuringUnitTestException extends RuntimeException {

	private static final long serialVersionUID = -4584041339906109902L;

	public DependentClassCallDuringUnitTestException() {
		super();
	}

	public DependentClassCallDuringUnitTestException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public DependentClassCallDuringUnitTestException(String message) {
		super(message);
	}

	public DependentClassCallDuringUnitTestException(Throwable cause) {
		super(cause);
	}

	
}

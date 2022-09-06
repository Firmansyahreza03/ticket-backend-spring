package com.lawencon.ticket.exception;

public class InvalidLoginException extends RuntimeException {

	private static final long serialVersionUID = 5557039500177760851L;
	
	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidLoginException(String message) {
		super(message);
	}
	
	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

}

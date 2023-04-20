package com.fproject.kabx.exception;

public class CarException extends Exception {
	
	public CarException(String message) {
		super(message);
	}
	
	public CarException(String message, Throwable cause){
		super(message, cause);
	}

}

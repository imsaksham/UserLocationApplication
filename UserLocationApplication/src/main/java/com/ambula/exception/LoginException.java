package com.ambula.exception;

public class LoginException extends RuntimeException {
	
	public LoginException() {
		
	}
	
	public LoginException(String message) {
		super(message);
	}
}

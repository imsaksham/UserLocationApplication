package com.ambula.exception;

public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException() {
		
	}
	
public UserAlreadyExistException(String message) {
		super(message);
	}
}

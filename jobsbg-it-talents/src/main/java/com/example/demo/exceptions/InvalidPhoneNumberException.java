package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPhoneNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPhoneNumberException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidPhoneNumberException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidPhoneNumberException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPhoneNumberException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPhoneNumberException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

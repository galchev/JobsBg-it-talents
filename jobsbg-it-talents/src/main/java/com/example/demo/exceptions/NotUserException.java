package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NotUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

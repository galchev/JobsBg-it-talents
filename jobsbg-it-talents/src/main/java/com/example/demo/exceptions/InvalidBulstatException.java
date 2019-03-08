package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidBulstatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372066578520157794L;

	public InvalidBulstatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidBulstatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidBulstatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidBulstatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidBulstatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

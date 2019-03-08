package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidEmailOrPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidEmailOrPasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public InvalidEmailOrPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmailOrPasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmailOrPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidEmailOrPasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

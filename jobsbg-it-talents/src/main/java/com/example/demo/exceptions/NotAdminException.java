package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAdminException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAdminException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAdminException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

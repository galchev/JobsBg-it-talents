package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidOfferOwnerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5254047132914035937L;

	public InvalidOfferOwnerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidOfferOwnerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public InvalidOfferOwnerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidOfferOwnerException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidOfferOwnerException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}

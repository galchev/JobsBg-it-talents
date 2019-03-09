package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AlreadyLoggedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6527358525756189066L;

	public AlreadyLoggedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AlreadyLoggedException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}

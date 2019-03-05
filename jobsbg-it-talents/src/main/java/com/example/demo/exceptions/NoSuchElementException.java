package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchElementException extends Exception{

	private static final long serialVersionUID = -2301562710230686532L;

	public NoSuchElementException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoSuchElementException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public NoSuchElementException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NoSuchElementException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NoSuchElementException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


}

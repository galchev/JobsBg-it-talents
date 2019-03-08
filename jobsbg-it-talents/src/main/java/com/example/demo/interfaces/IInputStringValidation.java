package com.example.demo.interfaces;

import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;

public interface IInputStringValidation {
	
	public static final int PHONE_NUMBER_SYMBOLS_COUNT = 10;
	public static final String PHONE_NUMBER_PREFIX = "08";
	public static final int PASSWORD_MIN_SYMBOLS = 5;
	public static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	
	
	default public void isValidName(String name, String regex) throws InvalidNameException {
		if(!(name.trim().length() >= 2) && name.matches(regex)) {
			throw new InvalidNameException("Sorry first or last name is invalid");
		}
		
		
	}
	
	
	
	default public void isValidEmailAndPassword(String email, String pass) throws InvalidEmailOrPasswordException {
		if(!email.matches(EMAIL_REGEX) ||
				!(pass.trim().length() >= PASSWORD_MIN_SYMBOLS)) {
			throw new InvalidEmailOrPasswordException("Invalid email or password");
		}
		
		
	}
			
	
	
	default public void isValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		if(!(phoneNumber.startsWith(PHONE_NUMBER_PREFIX) &&
				
				phoneNumber.length() == PHONE_NUMBER_SYMBOLS_COUNT)) {			
			throw new InvalidPhoneNumberException("Invalid phone number");
			
		}
	}
}

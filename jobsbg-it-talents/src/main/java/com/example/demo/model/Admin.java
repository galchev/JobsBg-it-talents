package com.example.demo.model;

public class Admin extends User{

	public Admin(Long id, String email, String password, String phoneNumber, String firstName, String lastName,
			boolean isDeleted) {
		super(id, email, password, phoneNumber, firstName, lastName, isDeleted);
		setAdmin(true);
		
	}

	


	
}

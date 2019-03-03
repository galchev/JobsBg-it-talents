package com.example.demo.model;

public class User extends Registration{
	
	private String firstName;
	private String lastName;
	private boolean isAdmin;

	public User(Long id, String email, String password, String phoneNumber,
			String firstName, String lastName, boolean isAdmin) {
		super(id, email, password, phoneNumber);
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
}

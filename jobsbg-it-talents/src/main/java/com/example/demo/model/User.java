package com.example.demo.model;

public class User extends Registration{
	
	private long id;
	private String firstName;
	private String lastName;
	private boolean isAdmin;

	public User(Long id, String email, String password, String phoneNumber,
			String firstName, String lastName, boolean isDeleted) {
		super(id, email, password, phoneNumber, false);
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}




	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

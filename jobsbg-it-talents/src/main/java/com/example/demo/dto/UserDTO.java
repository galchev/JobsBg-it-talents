package com.example.demo.dto;

public class UserDTO {
	
	private Long userId;
	private String email;
	private String password;
	private String phoneNumber;
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private String pictureUrl;
	private boolean isDeleted;
	private String firstName;
	private String lastName;
	private boolean isAdmin;
	
	public UserDTO(Long userId, String fisrtName, String lastName, String email, String password) {
		this.userId = userId;
		this.firstName = fisrtName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public UserDTO(long id, String firstName, String lastName, String email, String password, String phoneNumber,
			boolean isAdmin, boolean isDeleted) {
		
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isAdmin = isAdmin;
		this.isDeleted = isDeleted;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Long getUserId() {
		return userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public String getFirstName() {
		return firstName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}


	

	
	
}

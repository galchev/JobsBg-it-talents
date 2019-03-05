package com.example.demo.dto;

public class UserProfileDTO {

	private Long userId;
	private String email;
	private String password;
	private String phoneNumber;
	private String pictureUrl;
	private boolean isDeleted;
	private String firstName;
	private String lastName;
	private boolean isAdmin;
	
	
	public UserProfileDTO(long id, String firstName, String lastName, String email, String password, String phoneNumber,
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
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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

	public String getLastName() {
		return lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}


	
	
}

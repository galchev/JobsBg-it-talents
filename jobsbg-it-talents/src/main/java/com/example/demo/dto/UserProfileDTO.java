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
			boolean isAdmin, boolean isDeleted, String pictureUrl) {

		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isAdmin = isAdmin;
		this.isDeleted = isDeleted;
		this.pictureUrl = pictureUrl;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

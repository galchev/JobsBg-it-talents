package com.example.demo.dto;

public class RegistrationDTO {

	private Long id;
	private String email;
	private String password;
	private String phoneNumber;
	private String pictureUrl;
	private boolean isDeleted;
	
	public RegistrationDTO(Long id, String email, String password, String phoneNumber, String pictureUrl,
			boolean isDeleted) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.pictureUrl = pictureUrl;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
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
	
}

package com.example.demo.dto;

public class UserDTO {
	
	private Long userId;
	private String fisrtName;
	private String lastName;
	private String email;
	private String password;
	
	public UserDTO(Long userId, String fisrtName, String lastName, String email, String password) {
		this.userId = userId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getFisrtName() {
		return fisrtName;
	}
	

	
	
}

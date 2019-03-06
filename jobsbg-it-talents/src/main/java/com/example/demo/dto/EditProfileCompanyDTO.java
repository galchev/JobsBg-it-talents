package com.example.demo.dto;

public class EditProfileCompanyDTO {
	
	private String name;
	private String website;
	private String password;
	private String phoneNumber;
	private String pictureUrl;
	public EditProfileCompanyDTO(String name, String website, String password, String phoneNumber, String pictureUrl) {
		super();
		this.name = name;
		this.website = website;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.pictureUrl = pictureUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
}

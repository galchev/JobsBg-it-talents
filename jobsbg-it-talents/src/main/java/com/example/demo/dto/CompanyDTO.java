package com.example.demo.dto;

public class CompanyDTO {

	private Long companyId;
	private String email;
	private String password;
	private String phoneNumber;
	private String pictureUrl;
	private boolean isDeleted;
	private String name;
	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public boolean isDeleted() {
		return isDeleted;
	}
	private String website;
	private int bulstat;
	
	public CompanyDTO(Long companyId, String name, String email, String website, int bulstat) {
		this.companyId = companyId;
		this.name = name;
		this.email = email;
		this.website = website;
		this.bulstat = bulstat;
	}
	
	public int getBulstat() {
		return bulstat;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getWebsite() {
		return website;
	}
	
}

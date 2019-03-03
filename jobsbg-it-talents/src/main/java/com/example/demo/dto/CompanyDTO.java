package com.example.demo.dto;

public class CompanyDTO {

	private Long companyId;
	private String name;
	private String email;
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

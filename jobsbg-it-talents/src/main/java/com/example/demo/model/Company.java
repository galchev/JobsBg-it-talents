package com.example.demo.model;

public class Company extends Registration{

	private long id;
	private String name;
	private String website;
	private int bulstat;
	
	public Company(Long id, String email, String password, String phoneNumber,
			String name, String website, int bulstat, boolean isDeleted) {
		super(id, email, password, phoneNumber, isDeleted);
		this.name = name;
		this.bulstat = bulstat;
		this.website = website;
	}
	

}

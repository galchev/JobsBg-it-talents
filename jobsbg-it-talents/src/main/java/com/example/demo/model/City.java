package com.example.demo.model;

public class City {
	private long id;
	private String name;
	private long countryId;
	public City(long id, String name, long countryId) {
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public long getCountryId() {
		return countryId;
	}
}

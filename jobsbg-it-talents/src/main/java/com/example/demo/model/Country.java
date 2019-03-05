package com.example.demo.model;

public class Country {
	
	private long id;
	private String name;
	public Country(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}

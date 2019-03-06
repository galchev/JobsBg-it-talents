package com.example.demo.model;

public class JobLanguage {
	private long id;
	private String language;
	public long getId() {
		return id;
	}
	public String getLanguage() {
		return language;
	}
	public JobLanguage(long id, String language) {
		super();
		this.id = id;
		this.language = language;
	}
}

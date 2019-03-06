package com.example.demo.model;

public class JobType {
	private long id;
	private String type;
	public JobType(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
}

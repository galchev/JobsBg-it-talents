package com.example.demo.model;

public class JobLevel {
	private long id;
	private String level;
	public JobLevel(long id, String level) {
		super();
		this.id = id;
		this.level = level;
	}
	public long getId() {
		return id;
	}
	public String getLevel() {
		return level;
	}
	@Override
	public String toString() {
		return "JobLevel [id=" + id + ", level=" + level + "]";
	}
	
	
}

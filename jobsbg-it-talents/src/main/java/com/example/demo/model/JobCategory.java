package com.example.demo.model;

public class JobCategory {
	private long id;
	private String category;
	public JobCategory(long id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public long getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	@Override
	public String toString() {
		return "JobCategory [id=" + id + ", category=" + category + "]";
	}
}

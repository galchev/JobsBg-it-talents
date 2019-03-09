package com.example.demo.dto;

import java.sql.Date;

public class EditOfferDTO {
	private long id;
	private String title;
	private int salary;
	private long locationId;
	private long jobTypeId;
	private long jobLevelId;
	private long jobLanguageId;
	private long jobCategoryId;
	
	public EditOfferDTO(long id, String title, int salary, long locationId, long jobTypeId, long jobLevelId,
			long jobLanguageId, long jobCategoryId) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
		this.locationId = locationId;
		this.jobTypeId = jobTypeId;
		this.jobLevelId = jobLevelId;
		this.jobLanguageId = jobLanguageId;
		this.jobCategoryId = jobCategoryId;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public int getSalary() {
		return salary;
	}
	public long getLocationId() {
		return locationId;
	}
	public long getJobTypeId() {
		return jobTypeId;
	}
	public long getJobLevelId() {
		return jobLevelId;
	}
	public long getJobLanguageId() {
		return jobLanguageId;
	}
	public long getJobCategoryId() {
		return jobCategoryId;
	}



	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EditOfferDTO [id=" + id + ", title=" + title + ", salary=" + salary + ", locationId=" + locationId
				+ ", jobTypeId=" + jobTypeId + ", jobLevelId=" + jobLevelId + ", jobLanguageId=" + jobLanguageId
				+ ", jobCategoryId=" + jobCategoryId + "]";
	}


}

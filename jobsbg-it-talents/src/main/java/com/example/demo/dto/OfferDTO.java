package com.example.demo.dto;

import java.sql.Date;
import java.time.LocalDate;


public class OfferDTO {
	private long id;
	private String title;
	private int salary;
	private String date;
	private long locationId;
	private long jobTypeId;
	private long jobLevelId;
	private long jobLanguageId;
	private long jobCategoryId;
	private long companyRegId;

	public OfferDTO(long id, String title, int salary, long locationId, long jobTypeId, long jobLevelId,
			long jobLanguageId, long jobCategoryId, long companyRegId) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
		this.date ="1999-01-01";
		this.locationId = locationId;
		this.jobTypeId = jobTypeId;
		this.jobLevelId = jobLevelId;
		this.jobLanguageId = jobLanguageId;
		this.jobCategoryId = jobCategoryId;
		this.companyRegId = companyRegId;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getSalary() {
		return salary;
	}

	public String getDate() {
		return date;
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

	public Long getCompanyRegId() {
		return companyRegId;
	}
}

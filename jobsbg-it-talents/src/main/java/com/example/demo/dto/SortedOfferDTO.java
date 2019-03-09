package com.example.demo.dto;

import java.sql.Date;

public class SortedOfferDTO {
	private long id;
	private String title;
	private int salary;
	private Date date;
	private Long locationId;
	private Long jobTypeId;
	private Long jobLevelId;
	private Long jobLanguageId;
	private Long jobCategoryId;
	private Long companyRegId;
	private Long cityId;
	private Long countryId;
	
	
	public SortedOfferDTO(long id, String title, int salary, Date date, Long locationId, Long jobTypeId,
			Long jobLevelId, Long jobLanguageId, Long jobCategoryId, Long companyRegId, Long cityId,Long countryId) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
		this.date = date;
		this.locationId = locationId;
		this.jobTypeId = jobTypeId;
		this.jobLevelId = jobLevelId;
		this.jobLanguageId = jobLanguageId;
		this.jobCategoryId = jobCategoryId;
		this.companyRegId = companyRegId;
		this.cityId = cityId;
		this.countryId = countryId;
		
		
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


	public Date getDate() {
		return date;
	}


	public Long getLocationId() {
		return locationId;
	}


	public Long getJobTypeId() {
		return jobTypeId;
	}


	public Long getJobLevelId() {
		return jobLevelId;
	}


	public Long getJobLanguageId() {
		return jobLanguageId;
	}


	public Long getJobCategoryId() {
		return jobCategoryId;
	}


	public Long getCompanyRegId() {
		return companyRegId;
	}


	public Long getCityId() {
		return cityId;
	}


	public Long getCountryId() {
		return countryId;
	}
}

package com.example.demo.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Offer {
	private long id;
	private String title;
	private int salary;
	private LocalDate date;
	private long locationId;
	private long jobTypeId;
	private long jobLevelId;
	private long jobLanguageId;
	private long jobCategoryId;
	private long companyRegId;
	private JdbcTemplate jdbcTemplate;
	
	public Offer(Long id, String title, int salary, LocalDate date, long locationId, long jobTypeId, long jobLevelId,
			long jobLanguageId, long jobCategoryId, long companyRegId) {
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
	public LocalDate getDate() {
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
	public long getCompanyRegId() {
		return companyRegId;
	}

	
	
	
}

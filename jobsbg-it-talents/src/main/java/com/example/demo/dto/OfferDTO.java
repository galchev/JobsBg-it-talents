package com.example.demo.dto;

import java.sql.Date;
import java.time.LocalDate;


public class OfferDTO {
	private long id;
	private String title;
	private int salary;
	private Date date;
	private long locationId;
	private long jobTypeId;
	private long jobLevelId;
	private long jobLanguageId;
	private long jobCategoryId;
	private long companyRegId;

	public OfferDTO(long id, String title,int salary, Date date, long locationId, long jobTypeId, long jobLevelId,
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferDTO [date=" + date + "]";
	}



	public void setCompanyRegId(long companyRegId) {
		this.companyRegId = companyRegId;
	}

	public void setDate(Date date) {
		this.date = date;
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

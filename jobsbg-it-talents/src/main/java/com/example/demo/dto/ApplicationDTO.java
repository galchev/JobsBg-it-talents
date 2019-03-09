package com.example.demo.dto;

import java.sql.Date;

public class ApplicationDTO {
	

	private Long applicationId;
	private Date date;
	private Long offerId;
	private Long userId;
	
	
	public ApplicationDTO(long applicationId, Date date, long offerId, long userId) {
		this.applicationId = applicationId;
		this.date = date;
		this.offerId = offerId;
		this.userId = userId;
	}
	
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
//	public ApplicationDTO(long offerId, long userId) {
//	super();
//	this.offerId = offerId;
//	this.userId = userId;
//}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public long getApplicationId() {
		return applicationId;
	}
	public Date getDate() {
		return date;
	}
	public long getOfferId() {
		return offerId;
	}
	public long getUserId() {
		return userId;
	}

}

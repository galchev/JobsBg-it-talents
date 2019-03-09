package com.example.demo.dto;

import java.sql.Date;

public class OfferToCleanDTO {
	private Long id;
	private Date date;

	public OfferToCleanDTO(Long id,Date date) {
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	
	public Date getDate() {
		return date;
	}
	@Override
	public String toString() {
		return "OfferToCleanDTO [id=" + id + ", date=" + date + "]";
	}
}

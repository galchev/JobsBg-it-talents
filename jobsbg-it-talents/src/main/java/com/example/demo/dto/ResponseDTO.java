package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
	
	private String message;
	private LocalDateTime date;
	
	public ResponseDTO(String message) {
		this.message = message;
		this.date = LocalDateTime.now();
	}


	public String getMessage() {
		return message;
	}

	public LocalDateTime getDate() {
		return date;
	}
	

}

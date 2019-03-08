package com.example.demo;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.interfaces.IStringToSha1;

@SpringBootApplication
public class JobsbgItTalentsApplication {

	public static void main(String[] args) throws SQLException{
		LocalDate date = LocalDate.now();
		System.out.println(date);
		Date d = Date.valueOf(date);
		System.out.println(d);
		SpringApplication.run(JobsbgItTalentsApplication.class, args);
		

	}

}

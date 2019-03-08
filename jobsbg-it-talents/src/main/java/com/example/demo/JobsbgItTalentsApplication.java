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
		System.out.println(IStringToSha1.stringToSha1("qwerty"));
		SpringApplication.run(JobsbgItTalentsApplication.class, args);
		

	}

}

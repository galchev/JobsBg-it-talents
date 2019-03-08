package com.example.demo;


import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.interfaces.IStringToSha1;

@SpringBootApplication
public class JobsbgItTalentsApplication {

	public static void main(String[] args) throws SQLException{
		SpringApplication.run(JobsbgItTalentsApplication.class, args);
	}

}

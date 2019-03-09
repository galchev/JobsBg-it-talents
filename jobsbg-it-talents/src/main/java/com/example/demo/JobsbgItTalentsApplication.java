package com.example.demo;


import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.daemons.OldOfferCleaner;


@SpringBootApplication
public class JobsbgItTalentsApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException{

//		java.util.TimeZone.getDefault();
		SpringApplication.run(JobsbgItTalentsApplication.class, args);
		Thread cleaner = new Thread(new OldOfferCleaner());
		cleaner.setDaemon(true);
		cleaner.start();
		

	}

}

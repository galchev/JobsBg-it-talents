package com.example.demo;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.daemons.OldOfferCleaner;
import com.example.demo.interfaces.IStringToSha1;

@SpringBootApplication
public class JobsbgItTalentsApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
		SpringApplication.run(JobsbgItTalentsApplication.class, args);
		Thread cleaner = new Thread(new OldOfferCleaner());
		cleaner.setDaemon(true);
		cleaner.start();
		

	}

}

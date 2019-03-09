package com.example.demo;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.daemons.OldOfferCleaner;
import com.example.demo.interfaces.IStringToSha1;

import ch.qos.logback.core.util.TimeUtil;

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

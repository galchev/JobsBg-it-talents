package com.example.demo.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class Offer {
	private long id;
	private String title;
	private int salary;
	private LocalDate date;
	private long locationId;
	private long jobTypeId;
	private long jobLevelId;
	private long jobLanguageId;
	private long jobCategoryId;
	private long companyRegId;
	private JdbcTemplate jdbcTemplate;
	
	public Offer(Long id, String title, int salary, LocalDate date, long locationId, long jobTypeId, long jobLevelId,
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
	
	
	
	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public int getSalary() {
		return salary;
	}
	public LocalDate getDate() {
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
	public long getCompanyRegId() {
		return companyRegId;
	}

	public List<Country> getAllCountries() throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rsCountries = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.countries;");
		List<Country> countries = new LinkedList<>();
		while(rsCountries.next()) {
			countries.add(new Country(rsCountries.getLong(1),rsCountries.getString(2)));
		}
		return countries;
	}
	public List<City> getAllCities() throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rsCities = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.cities;");
		List<City> cities = new LinkedList<>();
		while(rsCities.next()) {
			cities.add(new City(rsCities.getLong(1),rsCities.getString(2),rsCities.getLong(3)));
		}
		return cities;
	}
	public List<Location> getAllLocations() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.locations;");
		List<Location> locations = new LinkedList<>();
		while(rs.next()) {
			locations.add(new Location(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getLong(4)));
		}
		return locations;
	}
	
	
	
}

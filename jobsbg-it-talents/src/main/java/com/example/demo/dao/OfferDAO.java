package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.OfferDTO;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.model.JobCategory;
import com.example.demo.model.JobLanguage;
import com.example.demo.model.JobLevel;
import com.example.demo.model.JobType;
import com.example.demo.model.Location;
@Component
public class OfferDAO {
	private static final String SELECT_ALL_OFFERS_QUERRY = "SELECT * FROM `jobs-bg`.offers";
	private JdbcTemplate jdbcTemplate;
	private List<City> cities = new LinkedList<>();
	private List<Country> countries = new LinkedList<>();
	private List<Location> locations = new LinkedList<>();
	private List<JobCategory> categories = new LinkedList<>();
	private List<JobLanguage> languages = new LinkedList<>();
	private List<JobLevel> levels = new LinkedList<>();
	private List<JobType> types = new LinkedList<>();
	
	
	
	//get all offers
	public List<OfferDTO> getAllOffers(String sortBy, Long companyId) throws SQLException{
		//this.addAllEnums();
		//System.out.println(this.categories);
		//System.out.println(this.levels);
		Connection con = jdbcTemplate.getDataSource().getConnection();		
		ResultSet rs = con.createStatement()
				.executeQuery(SELECT_ALL_OFFERS_QUERRY + ";");
		
		List<OfferDTO> offers = new LinkedList<>();
		while(rs.next()) {
			offers.add(new OfferDTO(rs.getLong(1), rs.getString(2),
					rs.getInt(3),rs.getLong(5),rs.getLong(6),
					rs.getLong(7),rs.getLong(8),rs.getLong(9),rs.getLong(10)));
		}
		return offers.stream().filter(offer -> companyId == null || offer.getCompanyRegId().equals(companyId))
				.sorted((o1, o2) -> {
					if (sortBy == null) return 1;
					switch (sortBy) {
					case "offerTitle" : return o1.getTitle().compareTo(o2.getTitle());
					case "salaryAsc" : return o1.getSalary() - o2.getSalary();
					case "salaryDsc" : return o2.getSalary() - o1.getSalary();
					default : return 1;
					}
				})
				.collect(Collectors.toList());
	}
	
	public void addAllEnums() throws SQLException {
		this.addAllCategories();
		this.addAllCities();
		this.addAllCountries();
		this.addAllLanguages();
		this.addAllLevels();
		this.addAllLocations();
		this.addAllTypes();
	}
	public void addAllCountries() throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rsCountries = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.countries;");
		while(rsCountries.next()) {
			this.countries.add(new Country(rsCountries.getLong(1),rsCountries.getString(2)));
		}
	}
	public void addAllCities() throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rsCities = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.cities;");
		while(rsCities.next()) {
			this.cities.add(new City(rsCities.getLong(1),rsCities.getString(2),rsCities.getLong(3)));
		}
	}
	public void addAllLocations() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.locations;");
		while(rs.next()) {
			locations.add(new Location(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getLong(4)));
		}
	}
	public void addAllCategories() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.job_category;");
		while(rs.next()) {
			this.categories.add(new JobCategory(rs.getLong(1),rs.getString(2)));
		}
	}
	public void addAllTypes() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.job_types;");
		while(rs.next()) {
			this.types.add(new JobType(rs.getLong(1),rs.getString(2)));
		}
	}
	public void addAllLanguages() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.job_languages;");
		while(rs.next()) {
			this.languages.add(new JobLanguage(rs.getLong(1),rs.getString(2)));
		}
	}
	public void addAllLevels() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.job_levels;");
		while(rs.next()) {
			this.levels.add(new JobLevel(rs.getLong(1),rs.getString(2)));
		}
	}
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<City> getCities() {
		return cities;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public List<JobCategory> getCategories() {
		return categories;
	}

	public List<JobLanguage> getLanguages() {
		return languages;
	}

	public List<JobLevel> getLevels() {
		return levels;
	}

	public List<JobType> getTypes() {
		return types;
	}
	
}

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
import com.example.demo.dto.SortedOfferDTO;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.model.JobCategory;
import com.example.demo.model.JobLanguage;
import com.example.demo.model.JobLevel;
import com.example.demo.model.JobType;
import com.example.demo.model.Location;

@Component
public class OfferDAO {
	private static final int COUNT_OF_CRITERIAS_WHICH_ARE_NOT_USED_TO_SEARCH = 8;
	private static final String SELECT_ALL_OFFERS_QUERRY = "SELECT * FROM `jobs-bg`.offers";
	private static final String SELECT_CITY_ID_WHERE_OFFER_ID = "SELECT c.city_id FROM offers o left join locations l on (o.location_id = l.location_id) left join cities c on (l.city_id = c.city_id)";
	private static final String SELECT_COUNTRY_ID_WHERE_OFFER_ID = "SELECT c.country_id FROM offers o \r\n"
			+ "left join locations l on (o.location_id = l.location_id)\r\n"
			+ "left join cities c on (l.city_id = c.city_id)\r\n"
			+ "left join countries n on (c.country_id = n.country_id)";
	private JdbcTemplate jdbcTemplate;
	private List<City> cities = new LinkedList<>();
	private List<Country> countries = new LinkedList<>();
	private List<Location> locations = new LinkedList<>();
	private List<JobCategory> categories = new LinkedList<>();
	private List<JobLanguage> languages = new LinkedList<>();
	private List<JobLevel> levels = new LinkedList<>();
	private List<JobType> types = new LinkedList<>();
//<<<<<<< HEAD
//	
//	private JdbcTemplate jdbc = new JdbcTemplate();
//	
//	//get all offers
//	public List<SortedOfferDTO> getAllOffers(String sortBy,Long cityId,Long countryId,Long jobCategoryId,Long jobTypeId,Long jobLevelId,Long jobLanguageId,String keyword,Long companyId) throws SQLException{
//		Connection con = jdbcTemplate.getDataSource().getConnection();		
//		ResultSet rs = con.createStatement()
//				.executeQuery(SELECT_ALL_OFFERS_QUERRY + ";");
//		
//		List<SortedOfferDTO> offers = new LinkedList<>();
//	
//		
//		while(rs.next()) {
//			
//			long tempCityId  = 0;
//=======

	// get all offers
	public List<SortedOfferDTO> getAllOffers(String sortBy, Long cityId, Long countryId, Long jobCategoryId,
			Long jobTypeId, Long jobLevelId, Long jobLanguageId, String keyword, Long companyId) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement().executeQuery(SELECT_ALL_OFFERS_QUERRY + ";");

		List<SortedOfferDTO> offers = new LinkedList<>();

		while (rs.next()) {

			long tempCityId = 0;
//>>>>>>> f105d70c00a6ba8a78317cc8a710c7a00261d031
			long tempCountryId = 0;
			ResultSet rs1 = con.createStatement()
					.executeQuery(SELECT_CITY_ID_WHERE_OFFER_ID + " where o.offer_id = " + rs.getLong(1) + ";");
			rs1.next();
			tempCityId = rs1.getLong(1);
			ResultSet rs2 = con.createStatement()
					.executeQuery(SELECT_COUNTRY_ID_WHERE_OFFER_ID + "where o.offer_id = " + rs.getLong(1) + " ;");
			rs2.next();
			tempCountryId = rs2.getLong(1);

			SortedOfferDTO o = new SortedOfferDTO(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getDate(4),
					rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10),
					tempCityId, tempCountryId);
			offers.add(o);
		}
		List<SortedOfferDTO> offersToReturn = new LinkedList<>();
		byte criteriaNullCount = COUNT_OF_CRITERIAS_WHICH_ARE_NOT_USED_TO_SEARCH;
		if (cityId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByCityId(offers, cityId);
		}
		if (countryId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByCountryId(offers, countryId);
		}
		if (jobTypeId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByJobTypeId(offers, jobTypeId);
		}
		if (jobCategoryId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByJobCategoryId(offers, jobCategoryId);
		}
		if (jobLanguageId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByJobLanguageId(offers, jobLanguageId);
		}
		if (jobLevelId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByJobLevelId(offers, jobLevelId);
		}
		if (keyword != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByKeyWord(offers, keyword);
		}
		if (companyId != null) {
			criteriaNullCount--;
			offersToReturn = this.sortOffersByCompanyId(offers, companyId);
		}
		if (criteriaNullCount == COUNT_OF_CRITERIAS_WHICH_ARE_NOT_USED_TO_SEARCH) {
			offersToReturn = offers.stream().collect(Collectors.toList());
		}

		return offersToReturn.stream().sorted((o1, o2) -> {
			if (sortBy == null)
				return 1;
			switch (sortBy) {
			case "offerTitle":
				return o1.getTitle().compareTo(o2.getTitle());
			case "salaryAsc":
				return o1.getSalary() - o2.getSalary();
			case "salaryDsc":
				return o2.getSalary() - o1.getSalary();
			case "newest":
				return o2.getDate().compareTo(o1.getDate());
			case "oldest":
				return o1.getDate().compareTo(o2.getDate());
			default:
				return 1;
			}
		}).collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByCityId(List<SortedOfferDTO> offersToSort, Long cityId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getCityId().equals(cityId)).collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByCountryId(List<SortedOfferDTO> offersToSort, Long countryId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getCountryId().equals(countryId))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByJobLevelId(List<SortedOfferDTO> offersToSort, Long jobLevelId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getJobLevelId().equals(jobLevelId))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByJobLanguageId(List<SortedOfferDTO> offersToSort, Long jobLanguageId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getJobLanguageId().equals(jobLanguageId))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByJobCategoryId(List<SortedOfferDTO> offersToSort, Long jobCategoryId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getJobCategoryId().equals(jobCategoryId))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByJobTypeId(List<SortedOfferDTO> offersToSort, Long jobTypeId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getJobTypeId().equals(jobTypeId))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByKeyWord(List<SortedOfferDTO> offersToSort, String keyword) {
		return offersToSort.stream()
				.filter(offer -> offer.getTitle().toLowerCase().contains(keyword.toLowerCase().trim()))
				.collect(Collectors.toList());
	}

	private List<SortedOfferDTO> sortOffersByCompanyId(List<SortedOfferDTO> offersToSort, Long companyId)
			throws SQLException {
		return offersToSort.stream().filter(offer -> offer.getCompanyRegId().equals(companyId))
				.collect(Collectors.toList());
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

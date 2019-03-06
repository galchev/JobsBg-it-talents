package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.OfferDTO;
@Component
public class OfferDAO {
	private static final String SELECT_ALL_OFFERS_QUERRY = "SELECT * FROM `jobs-bg`.offers";
	private JdbcTemplate jdbcTemplate;
	
	
	
	//get all offers
	public List<OfferDTO> getAllOffers(String sortBy, Long companyId) throws SQLException{
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
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}

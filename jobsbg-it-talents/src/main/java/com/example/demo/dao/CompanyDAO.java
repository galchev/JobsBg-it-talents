package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Country;

@Component
public class CompanyDAO {
	
	private static final String GET_ALL_COMPANIES = "select c.company_reg_id, c.name, c.website, c.bulstat, r.email, r.password, r.phone_number, r.picture_url\r\n" + 
			"from companies c \r\n" + 
			"left join registrations r on(c.company_reg_id = r.registration_id)";
	private static final byte USERS_WITH_SAME_ID_COUNT = 1;
	private JdbcTemplate jdbcTemplate;
	
	public List<CompanyDTO> getAllCompanies() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement()
				.executeQuery(GET_ALL_COMPANIES + ";");
		
		List<CompanyDTO> companies = new LinkedList<>();
		while(rs.next()) {
			companies.add(new CompanyDTO(rs.getLong(1), rs.getString(2),
					rs.getString(3), rs.getString(5), rs.getInt(4), 
					rs.getString(6), rs.getString(7), rs.getString(8)));
		}		
		
		return companies;
	}
	
	public CompanyProfileDTO getCompanyById(long id) throws SQLException, NoSuchElementException {
		
		Connection con = jdbcTemplate.getDataSource().getConnection();		
		ResultSet rs = con.createStatement()
				.executeQuery(GET_ALL_COMPANIES
						+ "where c.company_reg_id = '"+id+"';");
		CompanyProfileDTO companyToReturn = null;
		byte companiesCount = 0;
		while(rs.next()) {
			companiesCount++;
		companyToReturn = new CompanyProfileDTO(rs.getLong(1), rs.getString(2),
				rs.getString(3), rs.getString(5), rs.getInt(4), 
				rs.getString(6), rs.getString(7), rs.getString(8));
		}
			if(companiesCount == USERS_WITH_SAME_ID_COUNT) {
				return companyToReturn;
			}
			else {
				throw new NoSuchElementException("Company not found");
			}
	}
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

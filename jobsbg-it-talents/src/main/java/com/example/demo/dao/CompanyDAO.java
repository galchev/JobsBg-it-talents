package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.EditProfileCompanyDTO;
import com.example.demo.dto.EditUserProfileDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Country;

@Component
public class CompanyDAO {
	
	private static final int PHONE_NUMBER_SYMBOLS_COUNT = 10;
	private static final String PHONE_NUMBER_PREFIX = "08";
	
	private static final String GET_ALL_COMPANIES = "select c.company_reg_id, c.name, c.website, c.bulstat, r.email, r.password, r.phone_number, r.picture_url\r\n" + 
			"from companies c \r\n" + 
			"left join registrations r on(c.company_reg_id = r.registration_id)";
	private static final byte COMPANIES_WITH_SAME_ID_COUNT = 1;
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
			if(companiesCount == COMPANIES_WITH_SAME_ID_COUNT) {
				return companyToReturn;
			}
			else {
				throw new NoSuchElementException("Company not found");
			}
	}
	
	
	public CompanyProfileDTO getCompanyProfile(long id) throws SQLException, NoSuchElementException {
		return this.getCompanyById(id);
	}
	
	public void editProfileCompany(long id, EditProfileCompanyDTO c) throws SQLException  {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		try {
			PreparedStatement pst = con.prepareStatement("update registrations set password = ?, phone_number = ?, picture_url = ? where registration_id = '"+id+"';");
			pst.setString(1, c.getPassword());
			pst.setString(2, c.getPhoneNumber());
			pst.setString(3, c.getPictureUrl());
			
			isValidPhoneNumber(c.getPhoneNumber());
			pst.executeUpdate();
			
			PreparedStatement pst1 = con.prepareStatement("update companies set name = ?, website = ? where company_reg_id = '"+id+"';");
			pst1.setString(1, c.getName());
			pst1.setString(2, c.getWebsite());
			
			String regex = "[0-9]+";
			isValidName(c.getName(), regex);
			pst1.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.rollback();
			e.printStackTrace();
		}finally {
			con.setAutoCommit(true);
		}
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private void isValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		if(!(phoneNumber.startsWith(PHONE_NUMBER_PREFIX) &&
				phoneNumber.length() == PHONE_NUMBER_SYMBOLS_COUNT)) {
			throw new InvalidPhoneNumberException("Invalid phone number");
		}
	}
	public void isValidName(String name, String regex) throws InvalidNameException {
		if(!(name.trim().length() >= 2) && name.matches(regex)) {
			throw new InvalidNameException("Sorry first or last name is invalid");
		}
	}
}

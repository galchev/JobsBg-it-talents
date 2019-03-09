package com.example.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.EditOfferDTO;
import com.example.demo.dto.EditProfileCompanyDTO;
import com.example.demo.dto.EditUserProfileDTO;
import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.exceptions.NotOfferFoundException;
import com.example.demo.interfaces.IStringToSha1;
import com.example.demo.model.Country;

@Component
public class CompanyDAO implements IStringToSha1{
	
	
	private static final String UPDATE_OFFERS_QUERY_PREPARED_STATEMENT_WHERE_ID = "update `jobs-bg`.offers set title = ?, salary = ?, location_id = ?, job_type_id = ?, job_level_id = ?, job_language_id = ?, job_category_id = ?  where offer_id = ";
	private static final String SELECT_OFFERS_QUERY_WHERE_OFFER_ID = "SELECT * FROM `jobs-bg`.offers where offer_id = ";
	private static final String DELETE_OFFERS_QUERY_WHERE_OFFER_ID = "delete from `jobs-bg`.offers where offer_id = ";
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

			CompanyDTO c = new CompanyDTO(rs.getLong(1), rs.getString(2),
					rs.getString(5), rs.getString(3), rs.getInt(4), 
					rs.getString(6), rs.getString(7), rs.getString(8));
			companies.add(c);
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
				rs.getString(5), rs.getString(3), rs.getInt(4), 
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
			
			
			String passwordToSha1 = IStringToSha1.stringToSha1(c.getPassword());
			
			
			pst.setString(1, passwordToSha1);
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

	public long addNewOffer(OfferDTO offer, long id) throws SQLException {
		
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		try{PreparedStatement pst = (PreparedStatement) 
				con.prepareStatement("insert into offers(title, salary, date, location_id, job_type_id, job_level_id, job_language_id, job_category_id, company_reg_id) values(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		offer.setDate(Date.valueOf(LocalDate.now()));
			pst.setString(1, offer.getTitle());
			pst.setInt(2, offer.getSalary());
//			pst.setDate(3, offer.getDate());
//			pst.setDate(4, Date.valueOf(LocalDate.now()));
//			offer.setDate(Date.valueOf(LocalDate.now()));
			pst.setDate(3, offer.getDate());
			pst.setLong(4, offer.getLocationId());
			pst.setLong(5, offer.getJobTypeId());
			pst.setLong(6, offer.getJobLevelId());
			pst.setLong(7, offer.getJobLanguageId());
			pst.setLong(8, offer.getJobCategoryId());
			pst.setLong(9, id);
//			offer.setCompanyRegId(id);
			pst.executeUpdate();
		}
		catch(Exception e) {
			con.rollback();
			throw e;
		}
		finally {
			con.setAutoCommit(true);
		}
		return offer.getId();
	}

	public void editOffer(EditOfferDTO offer,long id) throws SQLException {

		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		
		
		try {
			PreparedStatement pst = con.prepareStatement
					(UPDATE_OFFERS_QUERY_PREPARED_STATEMENT_WHERE_ID+id+";");
			offer.setId(id);
			pst.setString(1, offer.getTitle());
			pst.setInt(2, offer.getSalary());
			pst.setLong(3, offer.getLocationId());
			pst.setLong(4, offer.getJobTypeId());
			pst.setLong(5, offer.getJobLevelId());
			pst.setLong(6, offer.getJobLanguageId());
			pst.setLong(7, offer.getJobCategoryId());
			System.out.println(offer);
			pst.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}finally {
			con.setAutoCommit(true);
		}
	}
	
	public Long isValidOfferOwning(long id) throws SQLException, NotOfferFoundException  {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement().executeQuery(SELECT_OFFERS_QUERY_WHERE_OFFER_ID+id+";");
		if(!rs.next()) {
			throw new NotOfferFoundException("Not offer with this id");
		}
		
	  
		Long tempId = rs.getLong(10);
		return tempId;
	}

	public void deleteOffer(Long offerId) {
		System.out.println("ID NA OFERTATA " +  offerId);
		Connection con = null;
		int rowsDeleted = 0;
		try {
			con = jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e1) {
			System.out.println("SQL exception in deleteOffer CompanyDAO");
		}
		try {
			rowsDeleted = con.createStatement().executeUpdate(DELETE_OFFERS_QUERY_WHERE_OFFER_ID+offerId+";");
		} catch (SQLException e) {
			System.out.println("SQL exception in deleteOffer CompanyDAO");
		}
	}
	
	
	
}


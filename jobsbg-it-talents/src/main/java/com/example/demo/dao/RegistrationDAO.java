	package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.interfaces.IInputStringValidation;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@Component
public class RegistrationDAO implements IInputStringValidation{
	

	
	
	private static final String GET_ALL_REGISTRATIONS = "select * from registrations";
	private JdbcTemplate jdbcTemplate;

	
	public List<Registration> getAllRegistrations() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement().executeQuery(GET_ALL_REGISTRATIONS);
		List<Registration> regs = new LinkedList<>();
		
		while(rs.next()) {
			regs.add(new Registration(rs.getLong(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getBoolean(6)));
		}
		
		return regs;
	}
	
	
	public long registerUser(UserProfileDTO user) throws Exception {
				
		
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		
		
		con.setAutoCommit(false);
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into registrations(email, password, phone_number, picture_url) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getPhoneNumber());
			pst.setString(4, user.getPictureUrl());

			
			isValidEmailAndPassword(user.getEmail(), user.getPassword());
			isValidPhoneNumber(user.getPhoneNumber());
	
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			long id = rs.getLong(1);
			user.setUserId(id);
			PreparedStatement usersPst = con.prepareStatement("insert into users (use_reg_id, first_name, last_name) values(?,?,?)");
			usersPst.setLong(1, id);
			usersPst.setString(2, user.getFirstName());
			usersPst.setString(3, user.getLastName());
			
			String regex = "[0-9]+";
			isValidName(user.getFirstName(), regex);
			isValidName(user.getLastName(), regex);
			
			usersPst.executeUpdate();

			
		}
		catch(Exception e) {
			con.rollback();
			System.out.println("EXCEPTIOOOOOON");
			throw e;
		}
		finally {
			System.out.println("finally executed");
			con.setAutoCommit(true);
		}
		return user.getUserId();	
	}

	
	public long registerCompany(CompanyDTO company) throws Exception {
		
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into registrations(email, password, phone_number, picture_url) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, company.getEmail());
			pst.setString(2, company.getPassword());
			pst.setString(3, company.getPhoneNumber());
			pst.setString(4, company.getPictureUrl());

			
			isValidEmailAndPassword(company.getEmail(), company.getPassword());
			isValidPhoneNumber(company.getPhoneNumber());
	
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			long id = rs.getLong(1);
			company.setCompanyId(id);;
			PreparedStatement usersPst = con.prepareStatement("insert into companies (company_reg_id, name, website, bulstat) values(?,?,?,?)");
			usersPst.setLong(1, id);
			usersPst.setString(2, company.getName());
			usersPst.setString(3, company.getWebsite());
			usersPst.setInt(4, company.getBulstat());

			
			String regex = "[0-9]+";
			isValidName(company.getName(), regex);
			
			usersPst.executeUpdate();

			
		}
		catch(Exception e) {
			con.rollback();
			System.out.println("EXCEPTIOOOOOON");
			throw e;
		}
		finally {
			System.out.println("finally executed");
			con.setAutoCommit(true);
		}
		return company.getCompanyId();	
	}
	


	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	public void isValidName(String name, String regex) throws InvalidNameException {
//		if(!(name.trim().length() >= 2) && name.matches(regex)) {
//			throw new InvalidNameException("Sorry first or last name is invalid");
//		}
//	}
//	
//	private void isValidEmailAndPassword(String email, String pass) throws InvalidEmailOrPasswordException {
//		if(!email.matches(EMAIL_REGEX) ||
//				!(pass.trim().length() >= PASSWORD_MIN_SYMBOLS)) {
//			throw new InvalidEmailOrPasswordException("Invalid email or password");
//		}
//	}
//
//	private void isValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
//		if(!(phoneNumber.startsWith(PHONE_NUMBER_PREFIX) &&
//				phoneNumber.length() == PHONE_NUMBER_SYMBOLS_COUNT)) {
//			throw new InvalidPhoneNumberException("Invalid phone number");
//		}
//	}
//	

	
	
}

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
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidBulstatException;
import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.interfaces.IInputStringValidation;
import com.example.demo.interfaces.IStringToSha1;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@Component
public class RegistrationDAO implements IInputStringValidation,IStringToSha1{
	

	
	
	private static final String INSERT_INTO_REGISTRATIONS_QUERY= "insert into registrations(email, password, phone_number, picture_url) values(?,?,?,?)";
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
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(INSERT_INTO_REGISTRATIONS_QUERY,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getEmail());
			String passwordToSha1 = IStringToSha1.stringToSha1(user.getPassword());
			pst.setString(2, passwordToSha1);
			pst.setString(3, user.getPhoneNumber());
			pst.setString(4, user.getPictureUrl());

			
			isValidEmailAndPassword(user.getEmail(), passwordToSha1);
			if(!this.isAvailableEmail(user.getEmail())) {
				throw new InvalidEmailOrPasswordException("Email is not free");
			}
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
			throw e;
		}
		finally {
			con.setAutoCommit(true);
		}
		return user.getUserId();	
	}

	
	public long registerCompany(CompanyProfileDTO company) throws Exception{
		
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(INSERT_INTO_REGISTRATIONS_QUERY,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, company.getEmail());
			String passwordToSha1 = IStringToSha1.stringToSha1(company.getPassword());
			pst.setString(2,passwordToSha1 );
			pst.setString(3, company.getPhoneNumber());
			pst.setString(4, company.getPictureUrl());

			
			isValidEmailAndPassword(company.getEmail(), passwordToSha1);
			if(!this.isAvailableEmail(company.getEmail())) {
				throw new InvalidEmailOrPasswordException("Email is not free");
			}
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
			if(!this.isValidBulstat(company.getBulstat())) {
				throw new InvalidBulstatException("Invalid bulstat");
			}
				usersPst.setInt(4, company.getBulstat());
			
			String regex = "[0-9]+";
			isValidName(company.getName(), regex);
			
			usersPst.executeUpdate();

			
		}
		catch(Exception e) {
			con.rollback();
			throw e;
		}
		
		finally {
			con.setAutoCommit(true);
		}
		return company.getCompanyId();	
	}
	
	public boolean isAvailableEmail(String email) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.registrations where email ='"+email+"';");
		byte count = 0;
		while(rs.next()) {
			count++;
		}
		if(count != 0) {
			return false;
		}
		else{
			return true;
		}
	}
	public boolean isValidBulstat(int bulstat) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM `jobs-bg`.companies where bulstat = '"+bulstat+"';");
		byte count = 0;
		while(rs.next()) {
			count++;
		}
		if(count != 0) {
			return false;
		}
		return true;
	}
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
		
	
}

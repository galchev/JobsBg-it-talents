package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.EditUserProfileDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@Component
public class UserDAO {
	private static final int USERS_WITH_SAME_ID_COUNT = 1;
	private static final int PHONE_NUMBER_SYMBOLS_COUNT = 10;
	private static final String PHONE_NUMBER_PREFIX = "08";

	private static final String SELECT_ALL_USERS_QUERRY = "select u.use_reg_id, u.first_name, u.last_name, r.email, r.password\r\n" + 
			"from users u \r\n" + 
			"left join registrations r on(u.use_reg_id = r.registration_id)";
	
	private static final String SELECT_USER_BY_ID_QUERY = "select u.use_reg_id, u.first_name, u.last_name, r.email, r.password, r.phone_number, u.is_admin, r.is_deleted, r.picture_url\r\n" + 
			"from users u \r\n" + 
			"left join registrations r on(u.use_reg_id = r.registration_id)";
	
	private JdbcTemplate jdbcTemplate;
		
	public List<UserDTO> getAllUsers() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();		
		ResultSet rs = con.createStatement()
				.executeQuery(SELECT_ALL_USERS_QUERRY + ";");
		
		List<UserDTO> users = new LinkedList<>();
		while(rs.next()) {
			users.add(new UserDTO(rs.getLong(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return users;
	}
	
	
	public UserProfileDTO getUserById(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();		
		ResultSet rs = con.createStatement()
				.executeQuery(SELECT_USER_BY_ID_QUERY
						+ "where u.use_reg_id = '"+id+"';");
		UserProfileDTO userToReturn = null;
		byte usersCount = 0;
		while(rs.next()) {
			usersCount++;
			userToReturn = new UserProfileDTO(rs.getLong(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getString(9));
//		userToReturn = new UserProfileDTO(rs.getLong(1), rs.getString(2),
//				rs.getString(3), rs.getString(4),rs.getString(5),
//				rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getString(9));
		}
			if(usersCount == USERS_WITH_SAME_ID_COUNT) {
				return userToReturn;
			}
			else {
				throw new NoSuchElementException("User not found");
			}
	}
	
	
	public RegistrationDTO login(LoginDTO user) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement().executeQuery("select * from registrations r\r\n" + 
				"where email = '"+user.getEmail()+"' and password = '"+user.getPassword()+"';");
		
		RegistrationDTO userToReturn = null;
		
		while(rs.next()) {
			userToReturn = new RegistrationDTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
		}
		
//		if(userToReturn == null) {
//			throw new NoSuchElementException("No such user");
//		}
		
		return userToReturn;
	}
	
	
	public void editProfile(long id, EditUserProfileDTO u) throws SQLException  {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.setAutoCommit(false);
		try {
			PreparedStatement pst = con.prepareStatement("update registrations set password = ?, phone_number = ?, picture_url = ? where registration_id = '"+id+"';");
			pst.setString(1, u.getPassword());
			pst.setString(2, u.getPhoneNumber());
			pst.setString(3, u.getPictureUrl());
			
			isValidPhoneNumber(u.getPhoneNumber());
			pst.executeUpdate();
			
			PreparedStatement pst1 = con.prepareStatement("update users set first_name = ?, last_name = ? where use_reg_id = '"+id+"';");
			pst1.setString(1, u.getFirstName());
			pst1.setString(2, u.getLastName());
			
			String regex = "[0-9]+";
			isValidName(u.getFirstName(), regex);
			isValidName(u.getFirstName(), regex);
			pst1.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.rollback();
			e.printStackTrace();
		}finally {
			con.setAutoCommit(true);
		}
	}
		
	
	
	public UserProfileDTO deleteProfile(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		con.createStatement().executeUpdate("update registrations set is_deleted = 1 where registration_id = "+id+";");
		UserProfileDTO u = this.getUserById(id);
		return u;
	}
	
	public UserProfileDTO getUserProfile(long id) throws SQLException, NoSuchElementException {
		return this.getUserById(id);
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

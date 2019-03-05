package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@Component
public class UserDAO {
	private static final int USERS_WITH_SAME_ID_COUNT = 1;

	private static final String SELECT_ALL_USERS_QUERRY = "select u.use_reg_id, u.first_name, u.last_name, r.email, r.password\r\n" + 
			"from users u \r\n" + 
			"left join registrations r on(u.use_reg_id = r.registration_id)";
	
	private static final String SELECT_USER_BY_ID_QUERY = "select u.use_reg_id, u.first_name, u.last_name, r.email, r.password, r.phone_number, u.is_admin, r.is_deleted\r\n" + 
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
	
	
	public UserDTO getUserById(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();		
		ResultSet rs = con.createStatement()
				.executeQuery(SELECT_USER_BY_ID_QUERY
						+ "where u.use_reg_id = '"+id+"';");
		UserDTO userToReturn = null;
		byte usersCount = 0;
		while(rs.next()) {
			usersCount++;
		userToReturn = new UserDTO(rs.getLong(1), rs.getString(2),
				rs.getString(3), rs.getString(4),rs.getString(5),
				rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
		}
			if(usersCount == USERS_WITH_SAME_ID_COUNT) {
				return userToReturn;
			}
			else {
				throw new NoSuchElementException("User not found");
			}
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

}

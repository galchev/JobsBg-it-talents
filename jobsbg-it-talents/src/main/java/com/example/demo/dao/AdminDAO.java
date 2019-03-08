package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;

@Component
public class AdminDAO {
	
	private JdbcTemplate jdbcTemplate;

	public long deleteProfile(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
//		ResultSet rs = con.createStatement().executeQuery("select * from registrations where registration_id = "+id+";\r\n" + 
//				"");
		
		con.createStatement().executeUpdate("update registrations set is_deleted = 1 where is_deleted = 0 and registration_id = "+id+";\r\n" + 
				"");
		
		System.out.println("---------" + id);
		
		return id;
	}
	
	
	public long deleteOffer(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		int rowsAffected = con.createStatement().executeUpdate("delete from offers where offer_id = "+id+";\r\n" + 
				"");
		
		if(rowsAffected <= 0) {
			throw new NoSuchElementException("Not offer found with id" + id);
		}
		
		System.out.println("====" + id);
		return id;
	}
	
	
	public boolean isAdmin(long id) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement().executeQuery("select count(*) from registrations r\r\n" + 
				"left join users u on(r.registration_id = u.use_reg_id)\r\n" + 
				"where u.is_admin = 1 and r.registration_id = "+id+";");
		int count = 0;
		while(rs.next()) {
			count++;
		}
		return count > 0 ? true : false;
	}
	
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

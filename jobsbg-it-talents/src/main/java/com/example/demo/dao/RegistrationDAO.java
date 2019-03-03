package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Registration;

@Component
public class RegistrationDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Registration> getAllRegistrations() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement().executeQuery("select * from registrations");
		List<Registration> regs = new LinkedList<>();
		
		while(rs.next()) {
			regs.add(new Registration(rs.getLong(1), rs.getString(2), rs.getString(3),
					rs.getString(4)));
		}
		
		return regs;
	}
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
}

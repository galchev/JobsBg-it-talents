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

@Component
public class CompanyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public List<CompanyDTO> getAllCompanies() throws SQLException{
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement()
				.executeQuery("select c.company_reg_id, c.name, c.website, c.bulstat, r.email\r\n" + 
						"from companies c \r\n" + 
						"left join registrations r on(c.company_reg_id = r.registration_id);");
		
		List<CompanyDTO> companies = new LinkedList<>();
		while(rs.next()) {
			companies.add(new CompanyDTO(rs.getLong(1), rs.getString(2),
					rs.getString(3), rs.getString(5), rs.getInt(4)));
		}		
		
		return companies;
	}
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

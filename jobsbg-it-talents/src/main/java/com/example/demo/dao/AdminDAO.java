package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.interfaces.IAdmin;

@Component
public class AdminDAO implements IAdmin{
	
	private JdbcTemplate jdbcTemplate;

	public long deleteProfile(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		
		Connection con1 = jdbcTemplate.getDataSource().getConnection();
		String deleteUserFromApps = "delete from applications where user_reg_id = "+id+";";
		deleteFromApplications(con1, deleteUserFromApps);
		
		con.createStatement().executeUpdate("update registrations set is_deleted = 1 where is_deleted = 0 and registration_id = "+id+";\r\n" + 
				"");
		
		System.out.println("---------" + id);
		
		return id;
	}
	
	
	public long deleteOffer(long id) throws SQLException, NoSuchElementException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		Connection con1 = jdbcTemplate.getDataSource().getConnection();
		String deleteOfferFromApps = "delete from applications where offer_id = "+id+";";
//		con1.createStatement().executeUpdate("delete from applications where offer_id = "+id+";");
		deleteFromApplications(con1, deleteOfferFromApps);
		int rowsAffected = con.createStatement().executeUpdate("delete from offers where offer_id = "+id+";\r\n" + 
				"");
		
		if(rowsAffected <= 0) {
			throw new NoSuchElementException("Not offer found with id " + id);
		}
		
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


	@Override
	public void deleteFromApplications(Connection con, String query) throws SQLException {
		con.createStatement().executeUpdate(query);
	}
}

package com.example.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.AdminDAO;
import com.example.demo.exceptions.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobsbgItTalentsApplicationTests {

	@Autowired
	private AdminDAO adminDao;
	
	private JdbcTemplate jdbc;
	
	@Test
	public void testDeleteProfileByAdmin() throws SQLException, NoSuchElementException {
		Connection con = jdbc.getDataSource().getConnection();
		int affectedRows = con.createStatement().executeUpdate("update registrations set is_deleted = 1 where is_deleted = 0 and registration_id = 32;");
		adminDao.deleteProfile(32);
		assertTrue(affectedRows > 0);
	}
	
	
	
	
	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	

}

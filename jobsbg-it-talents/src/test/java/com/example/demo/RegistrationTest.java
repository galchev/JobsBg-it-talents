package com.example.demo;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.RegistrationDAO;
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidBulstatException;
import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationTest {
	@Autowired
	protected RegistrationDAO dao;
	@Test
	public void testRegistrationUser() throws SQLException, InvalidNameException, InvalidEmailOrPasswordException, InvalidPhoneNumberException {
		int regSize = dao.getAllRegistrations().size();
		dao.registerUser(new UserProfileDTO(90, "Avram", "Grant", "chelsea@gmail.com", "torres", "0878337320", false, false, ""));
		int regSize2 = dao.getAllRegistrations().size();
		boolean areSame = false;
		if(regSize == regSize2) areSame = true;
		assertFalse(areSame);
	}
	
	@Test
	public void testRegistrationCompany() throws SQLException, InvalidNameException, InvalidEmailOrPasswordException, InvalidPhoneNumberException, InvalidBulstatException {
		int regSize = dao.getAllRegistrations().size();
		dao.registerCompany(new CompanyProfileDTO(null, "panasonic","panasonic@gmail.com","panasonic.com", 12345678, "password", "0879883383", ""));
		int regSize2 = dao.getAllRegistrations().size();
		boolean areSame = false;
		if(regSize == regSize2) areSame = true;
		assertFalse(areSame);
	}
	
	

}

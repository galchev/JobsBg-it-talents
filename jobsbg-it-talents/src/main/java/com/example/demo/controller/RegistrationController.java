package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RegistrationDAO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidBulstatException;
import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.model.Registration;

@RestController
public class RegistrationController {
	
	

	@Autowired
	private RegistrationDAO regDao;
	
	
	@GetMapping("/registrations")
	public List<Registration> getAllRegistrations(){
		try {
			return regDao.getAllRegistrations();
		} catch (SQLException e) {
			e.printStackTrace();
			return new LinkedList<>();
		}
	}
	
	@PostMapping("/registrationsUser")
	public long register(@RequestBody UserProfileDTO user) throws Exception {
		
		return this.regDao.registerUser(user);
		
	}
	
	@PostMapping("/registrationsCompany")
	public long register(@RequestBody CompanyDTO company) throws Exception{
		
		return this.regDao.registerCompany(company);
		
		
	}

	
}

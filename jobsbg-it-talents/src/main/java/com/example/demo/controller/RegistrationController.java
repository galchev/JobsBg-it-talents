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
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.InvalidBulstatException;
import com.example.demo.exceptions.InvalidEmailOrPasswordException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.model.Registration;

@RestController
public class RegistrationController {
	
	

	@Autowired
	private RegistrationDAO regDao;
	
	/*
	 * Get all registrations 
	 */
	@GetMapping("/registrations")
	public List<Registration> getAllRegistrations(){
		try {
			return regDao.getAllRegistrations();
		} catch (SQLException e) {
			System.out.println("SQL exception in /registrations method in Registration Controller");
			return new LinkedList<>();
		}
	}
	
	/*
	 * Register as User 
	 */
	
	@PostMapping("/registrationsUser")
	public long register(@RequestBody UserProfileDTO user) throws InvalidNameException, InvalidEmailOrPasswordException, InvalidPhoneNumberException {
		
		try {
			return this.regDao.registerUser(user);
		} catch (SQLException e) {
			return 0;
		}
		
	}
	
	/*
	 * Register as a Company
	 */
	
	@PostMapping("/registrationsCompany")
	public long register(@RequestBody CompanyProfileDTO company) throws InvalidNameException, InvalidPhoneNumberException, InvalidEmailOrPasswordException, InvalidBulstatException{
		
		try {
			return this.regDao.registerCompany(company);
		} catch (SQLException e) {
			return 0;
		}
		
		
	}

	
}

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
	public long register(@RequestBody UserDTO user) {
		
		try {
			return this.regDao.registerUser(user);
		} catch (Exception e) {
			System.out.println("CONTROLLER EXCEPTION");
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	@PostMapping("/registrationsCompany")
	public long register(@RequestBody CompanyDTO company) {
		
		try {
			return this.regDao.registerCompany(company);
		} catch (Exception e) {
			System.out.println("CONTROLLER EXCEPTION");
			e.printStackTrace();
			return -1;
		}
		
		
	}

	
}

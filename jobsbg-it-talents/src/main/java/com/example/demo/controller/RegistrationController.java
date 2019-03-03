package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RegistrationDAO;
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
}

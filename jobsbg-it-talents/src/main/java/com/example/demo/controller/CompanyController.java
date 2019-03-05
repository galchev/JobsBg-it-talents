package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CompanyDAO;
import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.CompanyProfileDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;

@RestController
public class CompanyController {

	@Autowired
	private CompanyDAO companyDao;
	
	
	@GetMapping("/companies")
	public List<CompanyDTO> getAllCompanies(){
		try {
			return this.companyDao.getAllCompanies();
		} catch (SQLException e) {
			e.printStackTrace();
			return new LinkedList<>();
		}
	}
	@GetMapping("/companies/{userId}")
	public CompanyProfileDTO getCompanyDetails(@PathVariable long userId) throws SQLException, NoSuchElementException {
			return companyDao.getCompanyById(userId);
	}
}

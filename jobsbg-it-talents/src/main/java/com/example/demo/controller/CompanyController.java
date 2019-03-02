package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CompanyDAO;

@RestController
public class CompanyController {

	@Autowired
	private CompanyDAO companyDao;
	
}

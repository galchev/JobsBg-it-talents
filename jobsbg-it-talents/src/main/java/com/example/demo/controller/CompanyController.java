package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	@GetMapping("/companies/{companyId}")
	public CompanyProfileDTO getCompanyDetails(@PathVariable long companyId) throws SQLException, NoSuchElementException {
			return companyDao.getCompanyById(companyId);
	}
	@GetMapping("/companyProfile")
	public CompanyProfileDTO getCompanyProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException {
		try {
			HttpSession session = request.getSession();
			if(!isLogged(session)) {
				response.setStatus(401);
				return null;
			}
			long id = (long) session.getAttribute("userId");
			System.out.println(id);
			return companyDao.getCompanyProfile(id);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}
	private boolean isLogged(HttpSession session) {
		return !(session.getAttribute("userId") == null);
	}
}

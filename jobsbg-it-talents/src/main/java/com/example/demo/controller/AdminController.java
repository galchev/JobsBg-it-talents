package com.example.demo.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.exceptions.NotAdminException;
import com.example.demo.interfaces.IRegistrationLogin;
import com.example.demo.model.User;

@RestController
public class AdminController implements IRegistrationLogin{
	
//	@Autowired
//	private UserDAO userDao;
	@Autowired
	private AdminDAO adminDao;
	
	@DeleteMapping("/deleteRegistration/{id}")
	public void deleteRegistration(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws NotAdminException, SQLException, NoSuchElementException{
		
			System.out.println("id" + id);
			HttpSession session = request.getSession();
			
			if(!isLogged(session)) {
				response.setStatus(401);
				return;
			} else {
				long currentUserId = (long) session.getAttribute("userId");
				System.out.println(currentUserId + "-------");
					if(!adminDao.isAdmin(currentUserId)) {
						throw new NotAdminException("Not authorize to delete profiles");
					}
			}
			
			adminDao.deleteProfile(id);	
	}
	
}

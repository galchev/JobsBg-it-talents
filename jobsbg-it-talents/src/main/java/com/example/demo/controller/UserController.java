package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@RestController
public class UserController {

	private static final int SESSION_MAX_INACTIVE_SECONDS = 10;
	@Autowired
	private UserDAO userDao;
	
	@GetMapping("/users")
	public List<UserDTO> getAllUsers(){
		try {
			return userDao.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			return new LinkedList<UserDTO>();
		}
	}
	@GetMapping("/users/{userId}")
	public UserProfileDTO getUserDetails(@PathVariable long userId) throws SQLException, NoSuchElementException {
			return userDao.getUserById(userId);
	}
	
	@PostMapping("/login")
	public void login(@RequestBody LoginDTO user, HttpServletRequest request) throws NoSuchElementException {
		try {
			RegistrationDTO u = userDao.login(user);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_SECONDS);
			session.setAttribute("userId", u.getId());
		} catch (SQLException | NoSuchElementException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			System.out.println("not user found");
			throw new NoSuchElementException("Not user found");
		}
	}
	
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	
	@GetMapping("/profile")
	public UserProfileDTO getProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException {
	
		try {
			HttpSession session = request.getSession();
			
			if(session.getAttribute("userId") == null) {
				response.setStatus(401);
				return null;
			}
			
			long id = (long) session.getAttribute("userId");
			return userDao.getUserProfile(id);
		} catch (SQLException e) {
			System.out.println("aaaaaaaaaaa");
			e.printStackTrace();
			return null;
		} catch (NoSuchElementException e) {
			System.out.println("dsadsadas");
			e.printStackTrace();
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}
	
	
	
}

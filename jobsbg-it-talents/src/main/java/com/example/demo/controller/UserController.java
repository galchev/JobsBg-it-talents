package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.EditUserProfileDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.AlreadyAppliedForThisOfferException;
import com.example.demo.exceptions.ApplicationNotFoundException;
import com.example.demo.exceptions.DeletedUserException;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.exceptions.NotOfferFoundException;
import com.example.demo.exceptions.NotUserException;
import com.example.demo.interfaces.IRegistrationLogin;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@RestController
public class UserController implements IRegistrationLogin{

	private static final int SESSION_MAX_INACTIVE_SECONDS = 60;
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
	public void login(@RequestBody LoginDTO user, HttpServletRequest request) throws NoSuchElementException, DeletedUserException {
		try {
			RegistrationDTO u = userDao.login(user);
			if(u.isDeleted()) {
				throw new DeletedUserException("This user was deleted");
			}
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_SECONDS);
			session.setAttribute("userId", u.getId());
			session.setAttribute("user", u);
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
	
	@DeleteMapping("/deleteProfile")
	public UserProfileDTO deleteProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException {
		try {
			HttpSession session = request.getSession();
			
			if(!isLogged(session)) {
				response.setStatus(401);
				return null;
			}
			
			long id = (long) session.getAttribute("userId");
			logout(request);
			return userDao.deleteProfile(id);
		}catch (SQLException e) {
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

	
	@PutMapping("/editUserProfile")
	public void editProfile(@RequestBody EditUserProfileDTO user, HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException  {
		try {
			HttpSession session = request.getSession();
			
			if(!isLogged(session)) {
				response.setStatus(401);
				return;
			}
			
			long id = (long) session.getAttribute("userId");
			
			userDao.editProfileUser(id, user);
			
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/profile")
	public UserProfileDTO getProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException {
	
		try {
			HttpSession session = request.getSession();
			
			if(!isLogged(session)) {
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
	
	
	@PostMapping("/applyForOffer/{offerId}")
	public void applyForOffer(@PathVariable long offerId,HttpServletRequest request, HttpServletResponse response) throws SQLException, NotOfferFoundException, AlreadyAppliedForThisOfferException, NotUserException {
		HttpSession session = request.getSession();
		if(!isLogged(session)) {
			response.setStatus(401);
			return;
		}
		
		long id = (long) session.getAttribute("userId");
		System.out.println("logged id " + id);
		
		userDao.applyForOffer(offerId, id);
	}
	
	@DeleteMapping("/deleteApplication/{appId}")
	public void deleteApplication0(@PathVariable long appId, HttpServletRequest request, HttpServletResponse response) throws SQLException, ApplicationNotFoundException, NotUserException {
		HttpSession session = request.getSession();
			
		if(!isLogged(session)) {
			response.setStatus(401);
			return;
		}
			
		long userId = (long) session.getAttribute("userId");
			
		userDao.deleteApplication(appId, userId);
	}
	
}

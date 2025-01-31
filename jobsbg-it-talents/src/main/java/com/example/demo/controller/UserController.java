package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.exceptions.AlreadyAppliedForThisOfferException;
import com.example.demo.exceptions.AlreadyLoggedException;
import com.example.demo.exceptions.ApplicationNotFoundException;
import com.example.demo.exceptions.DeletedUserException;
import com.example.demo.exceptions.InvalidNameException;
import com.example.demo.exceptions.InvalidPhoneNumberException;
import com.example.demo.exceptions.NoSuchElementException;
import com.example.demo.exceptions.NotOfferFoundException;
import com.example.demo.exceptions.NotUserException;
import com.example.demo.exceptions.UnauthorizedException;
import com.example.demo.interfaces.IRegistrationLogin;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@RestController
public class UserController implements IRegistrationLogin{

	private static final int SESSION_MAX_INACTIVE_SECONDS = 60;
	@Autowired
	private UserDAO userDao;

	/**
	 * Get All users 
	 */
	
	@GetMapping("/users")
	public List<UserDTO> getAllUsers(){
		try {
			return userDao.getAllUsers();
		} catch (SQLException e) {
			System.out.println("SQL exception in getAllUsers Method");
			return new LinkedList<UserDTO>();
		}
	}
	/*
	 * Get user by ID
	 */
	@GetMapping("/users/{userId}")
	public UserProfileDTO getUserDetails(@PathVariable long userId) throws NoSuchElementException {
			try {
				return userDao.getUserById(userId);
			} catch (SQLException e) {
				System.out.println("SQL Exception in getUserDetails method in User controller");
				return null;
			}
	}
	/**
	 * Login
	 */
	@PostMapping("/login")
	public ResponseDTO login(@RequestBody LoginDTO user, HttpServletRequest request) throws NoSuchElementException, DeletedUserException, AlreadyLoggedException {
		HttpSession session = request.getSession();
		if(isLogged(session)) {
			throw new AlreadyLoggedException("You are already logged ");
		}
		
		try {
			RegistrationDTO u = userDao.login(user);
			if(u.isDeleted()) {
				throw new DeletedUserException("This user was deleted");
			}
			session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_SECONDS);
			session.setAttribute("userId", u.getId());
			session.setAttribute("user", u);
			
			ResponseDTO resp = new ResponseDTO("Successfully logged");
			return resp;
				
		} catch (SQLException | NoSuchElementException e) {
			throw new NoSuchElementException("Registration not Found");
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Invalid email or password");
		}
	}
	/**
	 * Get current logged user's applications
	 */
	@GetMapping("/applications")
	public List<ApplicationDTO> getAllAplications(HttpServletRequest request) throws UnauthorizedException{
		HttpSession session = request.getSession();
		if(!isLogged(session)) {
			throw new UnauthorizedException("You are not logged !");
		}
		long id = (long) session.getAttribute("userId");
		try {
			 return userDao.getApplications(id);
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION in getAllAplications Method");
			return new LinkedList<ApplicationDTO>();
		}
	}
	/*
	 * Logout
	 */
	@PostMapping("/logout")
	public ResponseDTO logout(HttpServletRequest request) throws UnauthorizedException {
		
		HttpSession session = request.getSession();
		if(!isLogged(session)) {
			throw new UnauthorizedException(" You are not logged to logout ");
		}
		
		session.invalidate();
		return new ResponseDTO("Successfully logged out");
	}
	/**
	 * Delete current logged user's profile
	 */
	@DeleteMapping("/deleteProfile")
	public UserProfileDTO deleteProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, UnauthorizedException {
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
			System.out.println("SQL EXCEPTION in delete profile  Method");
			return null;
		} catch (NoSuchElementException e) {
			System.out.println("No Such Element ");
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}
	
	/**
	 * Edit current logged user's profile (request JSON body from Postman)
	 */
	
	@PutMapping("/editUserProfile")
	public void editProfile(@RequestBody EditUserProfileDTO user, HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, InvalidNameException, InvalidPhoneNumberException  {
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
			System.out.println("SQL EXCEPTION in edit profile  Method");
			return;
		}
	}
	/**
	 * Get current logged user's profile details
	 */
	@GetMapping("/profile")
	public UserProfileDTO getProfile(HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, UnauthorizedException {
		
		try {
			HttpSession session = request.getSession();
			
			if(!isLogged(session)) {
				response.setStatus(401);
				return null;
			}
			
			long id = (long) session.getAttribute("userId");
			return userDao.getUserProfile(id);
		} catch (SQLException e) {
			return null;
		} catch (NoSuchElementException e) {
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}

	/**
	 * Current logged user applies for offer by offer id
	 */
	@PostMapping("/applyForOffer/{offerId}")
	public void applyForOffer(@PathVariable long offerId, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, NotOfferFoundException, AlreadyAppliedForThisOfferException, NotUserException {
		HttpSession session = request.getSession();
		if (!isLogged(session)) {
			response.setStatus(401);
			return;
		}
		long id = (long) session.getAttribute("userId");
		userDao.applyForOffer(offerId, id);
	}
	/**
	 * Delete application
	 */
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

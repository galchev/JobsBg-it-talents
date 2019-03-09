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

	/*
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
	/*
	 * Login
	 */
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
			System.out.println("Exception in login method in UserController");
			return ;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Invalid email or password");
		}
	}
	/*
	 * Get current logged user's applications
	 */
	@GetMapping("/applications")
	public List<ApplicationDTO> getAllAplications(HttpServletRequest request){
		HttpSession session = request.getSession();
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
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	/*
	 * Delete current logged user's profile
	 */
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
			System.out.println("SQL EXCEPTION in delete profile  Method");
			return null;
		} catch (NoSuchElementException e) {
			System.out.println("No Such Element ");
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}
	
	/*
	 * Edit current logged user's profile (request JSON body from Postman)
	 */
	
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
			System.out.println("SQL EXCEPTION in edit profile  Method");
			return;
		} catch (Exception e) {
			System.out.println("EXCEPTION in delete profile  Method");
			return;
		}
	}
	/*
	 * Get current logged user's profile details
	 */
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
			return null;
		} catch (NoSuchElementException e) {
			return null;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("Session expired");
		}
	}

	/*
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
	/*
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

package com.example.demo.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Registration;
import com.example.demo.model.User;

@RestController
public class UserController {

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
	public UserDTO getUserDetails(@PathVariable long userId) {
		try {
			return userDao.getUserById(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
}

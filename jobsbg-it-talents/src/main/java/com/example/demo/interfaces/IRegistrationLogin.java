package com.example.demo.interfaces;

import javax.servlet.http.HttpSession;

public interface IRegistrationLogin {
	
	default public boolean isLogged(HttpSession session) {
		return !(session.getAttribute("userId") == null);
	}
}

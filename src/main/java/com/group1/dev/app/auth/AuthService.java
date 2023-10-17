package com.group1.dev.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.dev.app.services.IUserService;

@Service
public class AuthService {
	
	@Autowired
	private IUserService userService;
	
	public AuthResponse login(LoginRequest request) {
		
		return null;
	}

}

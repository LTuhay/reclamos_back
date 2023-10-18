package com.group1.dev.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group1.dev.app.Jwt.JwtService;
import com.group1.dev.app.model.dao.UserRepository;
import com.group1.dev.app.model.entity.EntityUser;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		UserDetails user = userRepository.findByUsername(request.username()).orElseThrow();
		String token = jwtService.generateJWT(user);
		return new AuthResponse(token);

	}

	public void register(RegisterRequest request) {
		EntityUser user = new EntityUser(request.nombre(), request.email(), request.dni(), request.edad(),
				request.username(), passwordEncoder.encode(request.password()), request.tipoPersona());

		userRepository.save(user);
	}

}

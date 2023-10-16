package com.group1.dev.app.services;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.User;

@Service
public class UserMapper implements Function<User,UserDTO> {

	@Override
	public UserDTO apply(User user) {
		
		return new UserDTO(
			user.getNombre(),
			user.getEmail(),
			user.getDni(),
			user.getEdad(),
			user.getUsername(),
			user.getTipoPersona()
			);
	}
}

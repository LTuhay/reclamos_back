package com.group1.dev.app.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.EntityUser;

@Service
public class UserMapper implements Function<EntityUser, UserDTO> {

	@Override
	public UserDTO apply(EntityUser user) {

		return new UserDTO(
				user.getNombre(),
				user.getEmail(),
				user.getDni(),
				user.getEdad(),
				user.getUsername(),
				user.getTipoPersona());
	}

}

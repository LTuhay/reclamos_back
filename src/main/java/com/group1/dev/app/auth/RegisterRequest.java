package com.group1.dev.app.auth;

import com.group1.dev.app.model.entity.TipoPersona;

public record RegisterRequest(

		String nombre, String email, Integer dni, Integer edad, String username, String password,
		TipoPersona tipoPersona

) {

}

package com.group1.dev.app.dto;

import com.group1.dev.app.model.entity.TipoPersona;

public record UserDTO (
	
	String nombre,
	String email,
	Integer dni,
	Integer edad,
	String username,
	TipoPersona tipoPersona

	) {}

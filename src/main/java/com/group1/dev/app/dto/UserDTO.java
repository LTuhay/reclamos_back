package com.group1.dev.app.dto;

import java.util.Map;

import com.group1.dev.app.model.entity.TipoPersona;

public record UserDTO (
	
	String nombre,
	String email,
	Integer dni,
	Integer edad,
	String username,
	TipoPersona tipoPersona

	) {
	
	
	public Map<String, Object> toMap() {
	    return Map.ofEntries(
	      Map.entry("nombre", nombre),
	      Map.entry("email", email),
	      Map.entry("dni", dni),
	      Map.entry("edad", edad),
	      Map.entry("username", username),
	      Map.entry("tipoPersona", tipoPersona.name())
	    );
	  }
}


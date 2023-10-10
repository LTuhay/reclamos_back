package com.group1.dev.app.model.dao;

import java.util.List;

import com.group1.dev.app.model.entity.Persona;

public interface IPersonaRepositoryCustom {
	
	public Persona findPersonaDni(int dni);
	
	public List<Persona> findPersonaApellido(String apellido);

}

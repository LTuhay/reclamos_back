package com.group1.dev.app.services;

import java.util.List;

import com.group1.dev.app.model.entity.Persona;
import com.group1.dev.app.model.entity.Unidad;

public interface IUnidadService {
	
	public List<Unidad> findAll();

	public Unidad findById(int id);

	public void save(Unidad unidad);

	public void update(int unidadId, Unidad unidad);
	
	public void setPersona(int unidadId, Persona persona);

	public void deleteById(int id);

}
